package me.marlon.leoner.musicando.events.aggregation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.BroadcastEnum;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.events.domain.game.*;
import me.marlon.leoner.musicando.events.domain.params.ClientAnswerResponse;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import me.marlon.leoner.musicando.events.service.*;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameAggregation {

    private final SocketService socketService;

    private final RabbitService rabbitService;

    private final ScheduleService scheduleService;

    private final GameService gameService;

    private final PlayerService playerService;

    private final QuestionService questionService;

    private final ResultService resultService;

    private void sendEventToRabbit(Event event) {
        rabbitService.sendMessage(Constants.EVENTS_QUEUE, event);
    }

    private void sendDelayedEventToRabbit(Event event, Integer delay) {
        scheduleService.executeAfterDelay(() -> sendEventToRabbit(event), delay);
    }

    public String createGame() {
        return gameService.createGame();
    }

    public Optional<Game> getGame(String gameCode) {
        return gameService.findGameByCode(gameCode);
    }

    public Game getGameOrException(String gameCode) throws ObjectNotFoundException {
        Optional<Game> game = getGame(gameCode);
        return game.orElseThrow(() -> new ObjectNotFoundException("no such game"));
    }

    private Optional<Player> getPlayer(Game game, String playerId) {
        return playerService.getPlayers(game).stream().filter(player -> player.getId().equals(playerId)).findFirst();
    }

    public Player getPlayerOrException(Game game, String playerId) throws ObjectNotFoundException {
        Optional<Player> player = getPlayer(game, playerId);
        return player.orElseThrow(() -> new ObjectNotFoundException("no such player"));
    }

    /**
     * Fires when the host connect to the server
     *
     * @param game       that host is connected
     * @param connection connection parameters
     */
    public void onHostConnect(Game game, ConnectionSocket connection) {
        game.setSecret(UUID.randomUUID().toString());
        game.setSessionId((connection.getSessionId()));
        game.setConnected(true);
        gameService.save(game);

        broadcast(BroadcastEnum.WELCOME, game.getSessionId(), game);
        log.info("Host {} connected in game {}", connection.getSessionId(), connection.getGameCode());
    }

    public void onHostReconnect(Game game, ConnectionSocket connection) {
        game.setSessionId(connection.getSessionId());
        game.setConnected(true);
        gameService.save(game);

        broadcast(BroadcastEnum.WELCOME, game.getSessionId(), game);
        log.info("Host {} reconnected in game {}", connection.getSessionId(), connection.getGameCode());
    }

    public void onHostDisconnect(Game game) {
        game.setConnected(false);
        gameService.save(game);

        scheduleService.executeAfterDelay(() -> onHostDestroy(game.getCode()), 30);
    }

    private void onHostDestroy(String gameCode) {
        try {
            Game game = getGameOrException(gameCode);
            if (game.isConnected()) return;

            questionService.remove(game);
            gameService.remove(game);
            log.info("Host game {} destroyed", game.getCode());
        } catch (Exception ex) {
            log.error("An error occurred while processing host destroy event in game {}: {}", gameCode, ex.getMessage());
        }
    }

    /**
     * Fires when the client connect to the server
     *
     * @param game       game that client is connected
     * @param connection connection parameters
     */
    public void onClientConnect(Game game, ConnectionSocket connection) {
        log.info("Player {} connected in game {}", connection.getSessionId(), connection.getGameCode());

        Player player = playerService.onPlayerConnect(game, connection);

        broadcast(BroadcastEnum.WELCOME, player.getSessionId(), player);
        broadcast(BroadcastEnum.UPDATE_GAME, connection.getSessionId(), game);
        broadcastHost(BroadcastEnum.PLAYER_NEW, game, player.toDTO());
    }

    public void onClientReconnect(Game game, Player player, ConnectionSocket connection) {
        log.info("Player {} reconnected in game {}", connection.getSessionId(), connection.getGameCode());

        playerService.onPlayerReconnect(game, player, connection);

        broadcast(BroadcastEnum.WELCOME, connection.getSessionId(), player);
        broadcast(BroadcastEnum.UPDATE_GAME, connection.getSessionId(), game);
        broadcastHost(BroadcastEnum.PLAYER_UPDATE, game, player.toDTO());
    }

    public void onClientDisconnect(Game game, Player player) {
        playerService.onPlayerDisconnect(player);

        scheduleService.executeAfterDelay(() -> onClientDestroy(game, player.getId()), 30);
        broadcastHost(BroadcastEnum.PLAYER_UPDATE, game, player.toDTO());
    }

    private void onClientDestroy(Game game, String playerId) {
        try {
            Player player = getPlayerOrException(game, playerId);
            if (player.isConnected()) return;

            playerService.onPlayerDestroy(game, player);
            broadcastHost(BroadcastEnum.PLAYER_REMOVE, game, player.toDTO());
            log.info("Player {} destroyed from game {}", player.getSessionId(), game.getCode());

            Optional<Player> optVip = playerService.getNextVipPlayer(game);
            optVip.ifPresent(vip -> {
                log.info("Player {} is the new VIP in game {}", player.getSessionId(), game.getCode());

                vip.setVip(true);
                playerService.save(vip);

                broadcast(BroadcastEnum.PLAYER_UPDATE, vip.getSessionId(), vip.toDTO());
                broadcastHost(BroadcastEnum.PLAYER_UPDATE, game, vip.toDTO());
            });
        } catch (Exception ex) {
            log.error("An error occurred while processing client {} destroy event in game {}: {}", playerId, game.getCode(), ex.getMessage());
        }
    }

    public void onClientUpdateAvatar(Player player, String avatar) {
        playerService.onPlayerUpdateAvatar(player, avatar);
    }

    public void onNumberOfRoundsChange(Game game, Integer numberOfRounds) {
        game.setNumberOfSongs(numberOfRounds);
        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
    }

    public void onRoundDurationChange(Game game, Integer roundDuration) {
        game.setRoundDuration(roundDuration);
        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
    }

    public void onPlaylistChange(Game game, Playlist playlist) {
        game.setPlaylist(playlist);
        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
    }

    public void onStartGame(Game game, RequestStartParams request) {
        // Generate questions and save them to the game
        questionService.createAndSaveQuestions(game, request);

        gameService.onGameStart(game);

        // Send 'START_GAME' event to host and dispatch 'PRE_ROUND' event to rabbit
        sendEventToRabbit(Event.instanceRoundPreLiveEvent(game.getCode()));
        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
        log.debug("Game '{}' started", game.getCode());
    }

    public void onResetGame(Game game) {
        game.reset();
        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
    }

    public void onPreStartRound(Game game) {
        log.debug("Round is about to start in game '{}'", game.getCode());

        // Get round question
        Question question = questionService.getNextRoundInGame(game);

        // Create Round instance
        Round round = new Round(question);
        game.setCurrentRound(round);
        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_ROUND, game, round.toLiveRound());
        sendDelayedEventToRabbit(Event.instanceRoundLiveEvent(game.getCode(), round), Constants.ROUND_LIVE_DELAY);
    }

    public void onStartRound(Game game) {
        log.debug("Round LIVE in game '{}'", game.getCode());

        Round round = game.getCurrentRound();
        round.setStartedAt(System.currentTimeMillis());
        round.setState(RoundStateEnum.LIVE);

        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_ROUND, game, round.toLiveRound());
        sendDelayedEventToRabbit(Event.instanceRoundFinishEvent(game.getCode(), round), game.getRoundDuration());
    }

    public void onFinishRound(Game game) {
        log.debug("Round finished in game '{}'", game.getCode());

        Round round = game.getCurrentRound();
        round.setState(RoundStateEnum.FINISHED);

        gameService.save(game);

        broadcastAll(BroadcastEnum.UPDATE_ROUND, game, round.toFinishedRound());
        // Notify each client with your round result
        notifyRoundResult(game, round);

        sendDelayedEventToRabbit(Event.instanceRoundSummaryEvent(game.getCode(), round), 3);
    }

    public void onSummaryRound(Game game) {
        log.debug("Showing results in game '{}'", game.getCode());

        Round round = game.getCurrentRound();
        round.setState(RoundStateEnum.SUMMARY);

        gameService.save(game);

        // Update round to all clients and host
        broadcastAll(BroadcastEnum.UPDATE_ROUND, game, round.toSummaryRound());

        Event event = game.isLastRound() ? Event.instanceGameFinishedEvent(game.getCode()) : Event.instanceRoundPreLiveEvent(game.getCode());
        sendDelayedEventToRabbit(event, Constants.ROUND_FINISH_DELAY);
    }

    public void onFinishGame(Game game) {
        game.setState(GameStateEnum.FINISHED);
        game.setCurrentRound(null);
        gameService.save(game);

        questionService.remove(game);

        broadcastAll(BroadcastEnum.UPDATE_GAME, game, game);
    }

    public void onClientAnswer(Game game, Player player, String answerId) {
        Round round = game.getCurrentRound();
        Song answer = round.getAnswer();

        int points = 0;
        long guessTime = 0;
        boolean correctAnswer = answer.getId().equals(answerId);
        if (correctAnswer) {
            Long answeredAt = System.currentTimeMillis();
            guessTime = answeredAt - round.getStartedAt();
            float ratioPoints = Constants.MAX_POINTS / (game.getRoundDuration() * 1000F);
            Integer lostPoints = Math.round(ratioPoints * guessTime);
            points = Constants.MAX_POINTS - lostPoints;

            player.incrementPoints(points);
            player.incrementRoundsCorrects();
            gameService.save(game);
        }
        log.debug("Player {}[{}] answer correct? {} and earn {} points", player.getName(), player.getId(), correctAnswer, points);

        RoundResult result = new RoundResult();
        result.setRound(round.getId());
        result.setPlayerId(player.getId());
        result.setCorrect(correctAnswer);
        result.setPoints(points);
        result.setGuessTime(guessTime);

        resultService.saveResult(game, round, result);
    }

    private void notifyRoundResult(Game game, Round round) {
        List<Player> players = playerService.getPlayers(game);
        for (Player player : players) {
            RoundResult result = resultService.getResultByRoundAndPlayer(game.getCode(), round.getId(), player.getId());
            ClientAnswerResponse response = Objects.isNull(result) ? new ClientAnswerResponse(player.getId(), false, 0) : new ClientAnswerResponse(player.getId(), result.isCorrect(), result.getPoints());
            broadcast(BroadcastEnum.PLAYER_ANSWER, player.getSessionId(), response);
        }
    }

    private void broadcast(BroadcastEnum context, String sessionId, Object object) {
        socketService.broadcast(context, sessionId, object);
    }

    private void broadcastHost(BroadcastEnum context, Game game, Object player) {
        socketService.broadcast(context, game.getSessionId(), player);
    }

    private void broadcastPlayers(BroadcastEnum context, Game game, Object object) {
        List<Player> players = playerService.getPlayers(game);
        players.forEach(player -> socketService.broadcast(context, player.getSessionId(), object));
    }

    private void broadcastAll(BroadcastEnum context, Game game, Object object) {
        broadcastPlayers(context, game, object);
        broadcastHost(context, game, object);
    }
}
