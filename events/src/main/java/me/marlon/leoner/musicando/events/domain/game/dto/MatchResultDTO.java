package me.marlon.leoner.musicando.events.domain.game.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MatchResultDTO {

    private String playerId;

    private Integer position;

    private Integer totalCorrectAnswers;

    private Integer totalPoints;

    private Integer averageGuessTime;
}
