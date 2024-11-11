package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Game;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.manager.repository.GameRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final GameRepository repository;

    private String generateId() {
        return RandomStringUtils.random(4, 0, CHARS.length() - 1, false, false, CHARS.toCharArray(), new SecureRandom());
    }

    public Optional<Game> findGameByCode(String code) {
        return repository.findById(code);
    }

    public void saveGame(Game game) {
        repository.save(game);
    }

    public String createGame() {
        while (true) {
            String id = generateId();
            Optional<Game> game = findGameByCode(id);
            if (game.isEmpty()) {
                saveGame(new Game(id));
                return id;
            }
        }
    }
}
