package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("results")
public class RoundResult {

    private String roundId;

    private String playerId;

    private boolean correct;

    private Integer points;

    private Long guessTime;

    public RoundResult(String roundId, String playerId) {
        this.roundId = roundId;
        this.playerId = playerId;
        this.correct = false;
        this.points = 0;
        this.guessTime = 0L;
    }
}
