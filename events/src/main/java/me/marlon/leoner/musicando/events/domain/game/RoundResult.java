package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoundResult {

    private Integer round;

    private String playerId;

    private boolean correct;

    private Integer points;

    private Long guessTime;
}
