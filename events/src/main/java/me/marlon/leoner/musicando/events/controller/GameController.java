package me.marlon.leoner.musicando.events.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.aggregation.GameAggregation;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameAggregation aggregation;

    @GetMapping
    public ResponseEntity<String> getGame() {
        return ResponseEntity.ok(aggregation.createGame());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Game> getGameByCode(@PathVariable String code) throws AbstractException {
        return ResponseEntity.ok(aggregation.getGameOrException(code));
    }
}
