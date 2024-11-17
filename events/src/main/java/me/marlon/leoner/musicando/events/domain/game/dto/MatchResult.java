package me.marlon.leoner.musicando.events.domain.game.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchResult {

    private String playerId;

    private Integer position;

    private Integer totalPoints;

    private Integer correctAnswers;

    private Long totalGuessTime;

    public MatchResult(String playerId) {
        this.playerId = playerId;
        this.totalPoints = 0;
        this.correctAnswers = 0;
        this.totalGuessTime = 0L;
    }
}
