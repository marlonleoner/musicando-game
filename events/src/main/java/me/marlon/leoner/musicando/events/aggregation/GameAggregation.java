package me.marlon.leoner.musicando.events.aggregation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.BroadcastEnum;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.domain.game.*;
import me.marlon.leoner.musicando.events.domain.game.dto.MatchResult;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import me.marlon.leoner.musicando.events.service.*;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameAggregation {

    private final SocketService socketService;
    private final RabbitService rabbitService;
    private final ScheduleService scheduleService;
    private final ManagerIntegration managerIntegration;
    private final GameService gameService;
    private final MatchService matchService;
    private final RoundService roundService;
    private final ResultService resultService;
    private final PlayerService playerService;

    private void sendEventToRabbit(Event event) {
        rabbitService.sendMessage(Constants.EVENTS_QUEUE, event);
    }

    private void sendDelayedEventToRabbit(Event event, Integer delay) {
        scheduleService.executeAfterDelay(() -> sendEventToRabbit(event), delay);
    }

    public String createGame() {
        return gameService.createGame();
    }

    public Optional<Game> getGame(String gameId) {
        return gameService.findGameById(gameId);
    }

    public Game getGameOrException(String gameId) throws EventException {
        Optional<Game> game = getGame(gameId);
        return game.orElseThrow(() -> new EventException("no such game", true));
    }

    public Optional<Match> getMatch(String matchId) {
        return matchService.findMatchById(matchId);
    }

    public Match getMatchOrException(String matchId) throws EventException {
        Optional<Match> match = getMatch(matchId);
        return match.orElseThrow(() -> new EventException("no such match", true));
    }

    private Optional<Player> getPlayer(String playerId) {
        return playerService.getPlayer(playerId);
    }

    public Player getPlayerOrException(String playerId) throws EventException {
        Optional<Player> player = getPlayer(playerId);
        return player.orElseThrow(() -> new EventException("no such player", true));
    }

    public Playlist getPlaylistOrException(String playlistId) throws EventException {
        Optional<Playlist> playlist = managerIntegration.getPlaylistById(playlistId);
        return playlist.orElseThrow(() -> new EventException("no such playlist", true));
    }

    /**
     * Fires when the host connect to the server
     *
     * @param game       that host is connected
     * @param connection connection parameters
     */
    public void onHostConnect(Game game, ConnectionSocket connection) {
        log.info("Host {} connected in game {}", connection.getSessionId(), game.getId());

        Match match = matchService.onCreate(game.getId());
        gameService.onCreate(game, connection.getSessionId(), match.getId());

        broadcast(BroadcastEnum.WELCOME, connection.getSessionId(), game);
        broadcast(BroadcastEnum.MATCH_UPDATE, connection.getSessionId(), match);
    }

    public void onHostReconnect(Game game, ConnectionSocket connection) {
        log.info("Host {} reconnected in game {}", connection.getSessionId(), game.getId());

        Match match = matchService.onReconnect(game.getId(), game.getCurrentMatchId());
        gameService.onReconnect(game, connection.getSessionId());

        broadcast(BroadcastEnum.WELCOME, connection.getSessionId(), game);
        broadcast(BroadcastEnum.MATCH_UPDATE, connection.getSessionId(), match);
        notifyPlayersToHost(game);
        notifyMatchResultIfFinished(game, match);
    }

    private void notifyPlayersToHost(Game game) {
        List<Player> players = playerService.getPlayers(game.getId());
        for (Player player : players) {
            broadcast(BroadcastEnum.PLAYER_NEW, game.getSessionId(), player);
        }
    }

    private void notifyMatchResultIfFinished(Game game, Match match) {
        if (!game.isFinished()) return;

        notifyGameResults(game, match);
    }

    public void onHostDisconnect(Game game, Match match) {
        gameService.onDisconnect(game);
        matchService.onDisconnect(match);

        broadcastPlayers(BroadcastEnum.GAME_UPDATE, game.getId(), game);
        broadcastPlayers(BroadcastEnum.MATCH_UPDATE, game.getId(), match);
        scheduleService.executeAfterDelay(() -> onHostDestroy(game.getId()), 30);
    }

    private void onHostDestroy(String gameId) {
        boolean isDestroyed = gameService.onDestroy(gameId);
        if (isDestroyed) log.info("Host game {} destroyed", gameId);
    }

    /**
     * Fires when the client connect to the server
     *
     * @param game       game that client is connected
     * @param connection connection parameters
     */
    public void onClientConnect(Game game, ConnectionSocket connection) {
        log.info("Player {} connected in game {}", connection.getSessionId(), game.getId());

        Player player = playerService.onPlayerConnect(connection, game.getId());
        Match match = matchService.getMatchOrNull(game.getCurrentMatchId());

        // Notify player
        broadcast(BroadcastEnum.WELCOME, connection.getSessionId(), player);
        broadcast(BroadcastEnum.GAME_UPDATE, connection.getSessionId(), game);
        broadcast(BroadcastEnum.MATCH_UPDATE, connection.getSessionId(), match);
        // Notify host
        broadcast(BroadcastEnum.PLAYER_NEW, game.getSessionId(), player.toDTO());
    }

    public void onClientReconnect(Game game, Player player, ConnectionSocket connection) {
        log.info("Player {} reconnected in game {}", connection.getSessionId(), game.getId());

        playerService.onPlayerReconnect(player, connection, game.getId());
        Match match = matchService.getMatchOrNull(game.getCurrentMatchId());

        // Notify player
        broadcast(BroadcastEnum.WELCOME, connection.getSessionId(), player);
        broadcast(BroadcastEnum.GAME_UPDATE, connection.getSessionId(), game);
        broadcast(BroadcastEnum.MATCH_UPDATE, connection.getSessionId(), match);
        // Notify host
        broadcast(BroadcastEnum.PLAYER_UPDATE, game.getSessionId(), player.toDTO());
    }

    public void onClientDisconnect(Game game, Player player) {
        playerService.onPlayerDisconnect(player);

        scheduleService.executeAfterDelay(() -> onClientDestroy(game, player.getId()), 30);
        broadcast(BroadcastEnum.PLAYER_UPDATE, game.getSessionId(), player.toDTO());
    }

    private void onClientDestroy(Game game, String playerId) {
        try {
            Player player = getPlayerOrException(playerId);
            if (player.isConnected()) return;

            playerService.onPlayerDestroy(game, player);
            broadcast(BroadcastEnum.PLAYER_REMOVE, game.getSessionId(), player.toDTO());
            log.info("Player {} destroyed from game {}", player.getSessionId(), game.getId());

            Optional<Player> optVip = playerService.getNextVipPlayer(game.getId());
            optVip.ifPresent(vip -> {
                log.info("Player {} is the new VIP in game {}", player.getSessionId(), game.getId());

                vip.setVip(true);
                playerService.save(vip);

                broadcast(BroadcastEnum.PLAYER_UPDATE, vip.getSessionId(), vip.toDTO());
                broadcast(BroadcastEnum.PLAYER_UPDATE, game.getSessionId(), vip.toDTO());
            });
        } catch (Exception ex) {
            log.error("An error occurred while processing client {} destroy event in game {}: {}", playerId, game.getId(), ex.getMessage());
        }
    }

    public void onNumberOfRoundsChange(Game game, Match match, Integer numberOfRounds) {
        matchService.onNumberOfRoundsChange(match, numberOfRounds);

        broadcastAll(BroadcastEnum.MATCH_UPDATE, game, match);
    }

    public void onRoundDurationChange(Game game, Match match, Integer roundDuration) {
        matchService.onRoundDurationChange(match, roundDuration);

        broadcastAll(BroadcastEnum.MATCH_UPDATE, game, match);
    }

    public void onPlaylistChange(Game game, Match match, String playlistId) {
        matchService.onPlaylistChange(match, playlistId);

        broadcastAll(BroadcastEnum.MATCH_UPDATE, game, match);
    }

    public void onGameStart(Game game, Match match, RequestStartParams request) {
        log.debug("Game '{}' started", game.getId());

        List<String> rounds = roundService.createAndSaveRounds(match, request);
        matchService.onStart(match, rounds);
        gameService.onStart(game);

        sendEventToRabbit(Event.instanceRoundPreLiveEvent(game.getId()));
        broadcastAll(BroadcastEnum.GAME_UPDATE, game, game);
    }

    public void onGameFinish(Game game, Match match) {
        matchService.onFinish(match);
        gameService.onFinish(game);

        notifyGameResults(game, match);
        broadcastAll(BroadcastEnum.GAME_UPDATE, game, game);
        broadcastAll(BroadcastEnum.MATCH_UPDATE, game, match);
    }

    public void onGameReset(Game game, Match match) {
        Match other = matchService.onReset(match, game.getId());
        gameService.onReset(game, other.getId());

        broadcastAll(BroadcastEnum.GAME_UPDATE, game, game);
        broadcastAll(BroadcastEnum.MATCH_UPDATE, game, other);
    }

    public void onRoundPreLive(Game game, Match match) {
        log.debug("Round is about to start in game '{}'", game.getId());

        matchService.onPreLive(match);
        Round round = roundService.onPreLive(match.getCurrentRoundId());

        broadcastAll(BroadcastEnum.ROUND_UPDATE, game, round.toLiveRound());
        sendDelayedEventToRabbit(Event.instanceRoundLiveEvent(game.getId(), round), Constants.ROUND_LIVE_DELAY);
    }

    public void onRoundLive(Game game, Match match) {
        log.debug("Round LIVE in game '{}'", game.getId());

        Round round = roundService.onLive(match.getCurrentRoundId());

        broadcastAll(BroadcastEnum.ROUND_UPDATE, game, round.toLiveRound());
        sendDelayedEventToRabbit(Event.instanceRoundFinishEvent(game.getId(), round), match.getRoundDuration());
    }

    public void onRoundFinish(Game game, Match match) {
        log.debug("Round finished in game '{}'", game.getId());

        Round round = roundService.onFinish(match.getCurrentRoundId());

        notifyRoundResults(game, round);
        broadcastAll(BroadcastEnum.ROUND_UPDATE, game, round.toFinishedRound());
        sendDelayedEventToRabbit(Event.instanceRoundSummaryEvent(game.getId(), round), 3);
    }

    public void onRoundSummary(Game game, Match match) {
        log.debug("Showing results in game '{}'", game.getId());

        Round round = roundService.onSummary(match.getCurrentRoundId());

        // Update round to all clients and host
        broadcastAll(BroadcastEnum.ROUND_UPDATE, game, round.toSummaryRound());

        Event event = match.isLastRound() ? Event.instanceGameFinishedEvent(game.getId()) : Event.instanceRoundPreLiveEvent(game.getId());
        sendDelayedEventToRabbit(event, Constants.ROUND_FINISH_DELAY);
    }

    public void onClientAnswer(Game game, Match match, Player player, String answerId) {
        Round round = roundService.getRound(match.getCurrentRoundId());
        if (Objects.isNull(round) || !round.isLive()) return;

        Song answer = round.getAnswer();

        int points = 0;
        long guessTime = 0;
        boolean correctAnswer = answer.getId().equals(answerId);
        if (correctAnswer) {
            Long answeredAt = System.currentTimeMillis();
            guessTime = answeredAt - round.getStartedAt();
            float ratioPoints = Constants.MAX_POINTS / (match.getRoundDuration() * 1000F);
            Integer lostPoints = Math.round(ratioPoints * guessTime);
            points = Constants.MAX_POINTS - lostPoints;

            player.incrementPoints(points);
            player.incrementRoundsCorrects();
            gameService.save(game);
        }
        log.debug("Player {}[{}] answer correct? {} and earn {} points", player.getName(), player.getId(), correctAnswer, points);

        resultService.onAnswer(round.getId(), player.getId(), correctAnswer, points, guessTime);
    }

    private void notifyRoundResults(Game game, Round round) {
        notifyRoundResultsToPlayer(game.getId(), round.getId()); // Notify each client with your round result
        notifyRoundResultsToHost(game.getSessionId(), round.getId()); // Notify host with your round result
    }

    private void notifyRoundResultsToPlayer(String gameId, String roundId) {
        List<Player> players = playerService.getPlayers(gameId);
        for (Player player : players) {
            RoundResult result = resultService.onRoundResult(roundId, player.getId());
            broadcast(BroadcastEnum.ROUND_RESULT, player.getSessionId(), result);
        }
    }

    private void notifyRoundResultsToHost(String sessionId, String roundId) {
        List<RoundResult> results = resultService.getOrderedRoundResult(roundId);
        broadcast(BroadcastEnum.ROUND_RESULT, sessionId, results);
    }

    private void notifyGameResults(Game game, Match match) {
        List<MatchResult> results = resultService.onMatchResult(match.getRounds());
        notifyGameResultsToPlayers(game.getId(), results);
        notifyGameResultsToHost(game, results);
    }

    private void notifyGameResultsToPlayers(String gameId, List<MatchResult> results) {
        List<Player> players = playerService.getPlayers(gameId);
        for (Player player : players) {
            Optional<MatchResult> playerResult = results.stream().filter(result -> player.getId().equals(result.getPlayerId())).findFirst();
            broadcast(BroadcastEnum.MATCH_RESULT, player.getSessionId(), playerResult.orElse(null));
        }
    }

    private void notifyGameResultsToHost(Game game, List<MatchResult> results) {
        broadcast(BroadcastEnum.MATCH_RESULT, game.getSessionId(), results);
    }

    private void broadcast(BroadcastEnum context, String sessionId, Object object) {
        socketService.broadcast(context, sessionId, object);
    }

    private void broadcastPlayers(BroadcastEnum context, String gameId, Object object) {
        List<Player> players = playerService.getPlayers(gameId);
        players.forEach(player -> socketService.broadcast(context, player.getSessionId(), object));
    }

    private void broadcastAll(BroadcastEnum context, Game game, Object object) {
        broadcastPlayers(context, game.getId(), object);
        broadcast(context, game.getSessionId(), object);
    }
}
