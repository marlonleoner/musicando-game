package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.GameStateEnum;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.game.Question;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.repository.GameRepository;
import me.marlon.leoner.musicando.events.utils.Utils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository repository;

    private final MongoTemplate mongoTemplate;

    public String createGame() {
        while (true) {
            String id = Utils.generateId();
            Optional<Game> game = findGameByCode(id);
            if (game.isEmpty()) {
                save(new Game(id));
                return id;
            }
        }
    }

    public Game save(Game game) {
        return repository.save(game);
    }

    public void remove(Game game) {
        repository.delete(game);
    }

    public Optional<Game> findGameByCode(String code) {
        return repository.findById(code);
    }

    public void onGameStart(Game game) {
        game.setState(GameStateEnum.LIVE);
        save(game);
    }
}
