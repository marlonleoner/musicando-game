package me.marlon.leoner.musicando.manager.aggregation;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Game;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.manager.service.GameService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameAggregation {

    private final GameService gameService;

    public Game findGameOrException(String code) {
        return gameService.findGameByCode(code).orElseThrow(() -> new ObjectNotFoundException("no such game"));
    }

    public String createGame() {
        return gameService.createGame();
    }
}
