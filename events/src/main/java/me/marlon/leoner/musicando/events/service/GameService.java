package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.cache.CacheEnum;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.GameStateEnum;
import me.marlon.leoner.musicando.events.repository.GameRepository;
import me.marlon.leoner.musicando.events.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private static final CacheEnum CACHE_ENUM = CacheEnum.GAME;

    private final GameRepository repository;
    private final RedisService cacheService;

    public String createGame() {
        while (true) {
            String id = Utils.generateId();
            Optional<Game> game = findGameById(id);
            if (game.isEmpty()) {
                createAndSave(id);
                return id;
            }
        }
    }

    private void createAndSave(String id) {
        Game game = new Game();
        game.setId(id);
        game.setState(GameStateEnum.LOBBY);
        save(game);
    }

    public Game save(Game game) {
        return repository.save(game);
    }

    public void remove(Game game) {
        repository.delete(game);
    }

    public Optional<Game> findGameById(String gameId) {
        return repository.findById(gameId);
    }

    public String getHostSessionId(String gameId) {
        Optional<Game> game = findGameById(gameId);
        return game.map(Game::getSessionId).orElse(null);
    }


    /**
     * Fires when host connect to game for the first time
     *
     * @param game      the game
     * @param sessionId current host session id
     * @param matchId   current match id
     */
    public void onCreate(Game game, String sessionId, String matchId) {
        game.setSecret(UUID.randomUUID().toString());
        game.setSessionId(sessionId);
        game.setConnected(true);
        game.setCurrentMatchId(matchId);

        save(game);
    }

    public void onReconnect(Game game, String sessionId) {
        game.setSessionId(sessionId);
        game.setConnected(true);

        save(game);
    }

    public void onDisconnect(Game game) {
        game.setConnected(false);

        save(game);
    }

    public boolean onDestroy(String gameId) {
        Optional<Game> optionalGame = findGameById(gameId);
        if (optionalGame.isEmpty()) return false;

        Game game = optionalGame.get();
        if (game.isConnected()) return false;

        remove(game);
        return true;
    }

    public void onStart(Game game) {
        game.setState(GameStateEnum.LIVE);

        save(game);
    }

    public void onFinish(Game game) {
        game.setState(GameStateEnum.FINISHED);

        save(game);
    }

    public void onReset(Game game, String matchId) {
        game.setState(GameStateEnum.LOBBY);
        game.setCurrentMatchId(matchId);

        save(game);
    }
}
