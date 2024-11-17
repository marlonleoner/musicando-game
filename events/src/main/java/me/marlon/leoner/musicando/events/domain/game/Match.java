package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document("matches")
public class Match {

    @Id
    private String id;

    private String gameId;

    private MatchStateEnum state;

    private Integer numberOfSongs;

    private Integer roundDuration;

    private String playlistId;

    private Integer roundNumber;

    private List<String> rounds;

    private Date createdAt;

    private Date startedAt;

    private Date finishedAt;

    public Match(String gameId) {
        this.id = UUID.randomUUID().toString();
        this.gameId = gameId;
        this.state = MatchStateEnum.PREPARING;
        this.createdAt = new Date();
    }

    public String getCurrentRoundId() {
        return rounds.get(roundNumber - 1);
    }

    public void incrementRoundNumber() {
        roundNumber++;
    }

    public boolean isLastRound() {
        return numberOfSongs.equals(roundNumber);
    }

    public boolean isFinished() {
        return MatchStateEnum.FINISHED.equals(state);
    }
}
