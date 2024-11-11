package me.marlon.leoner.musicando.manager.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.aggregation.GameAggregation;
import me.marlon.leoner.musicando.manager.domain.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameAggregation aggregation;

    @GetMapping("/{code}")
    public ResponseEntity<Game> getGame(@PathVariable("code") String code) {
        return ResponseEntity.ok(aggregation.findGameOrException(code));
    }

    @PostMapping
    public ResponseEntity<String> createGame() {
        return ResponseEntity.ok(aggregation.createGame());
    }
}
