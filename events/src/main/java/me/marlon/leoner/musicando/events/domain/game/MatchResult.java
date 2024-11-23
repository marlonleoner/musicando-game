package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchResult {

    private Integer position;

    private String playerId;

    private Integer correctAnswers;

    private Integer totalPoints;

    private Integer totalGuessTime;
}
