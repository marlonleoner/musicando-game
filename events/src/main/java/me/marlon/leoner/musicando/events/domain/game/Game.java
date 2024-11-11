package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Game {

    @Id
    private String code;

    private String sessionId;

    private String secret;

    private boolean connected;

    private GameStateEnum state;

    private Integer numberOfSongs;

    private Integer roundDuration;

    @DBRef
    private Playlist playlist;

    @DBRef
    private List<Question> questions;

    private Round currentRound;

    public Game(String code) {
        this.code = code;
        this.state = GameStateEnum.LOBBY;
    }

    public void reset() {
        this.state = GameStateEnum.LOBBY;
        this.numberOfSongs = null;
        this.roundDuration = null;
        this.currentRound = null;
    }

    public Integer getNextRoundNumber() {
        return Objects.isNull(currentRound) ? 1 : currentRound.getId() + 1;
    }

    public boolean isSecretValid(String other) {
        return Objects.nonNull(secret) && secret.equals(other);
    }

    public boolean isConfigured() {
        return Objects.nonNull(secret);
    }

    private boolean isState(GameStateEnum other) {
        return Objects.nonNull(state) && state.equals(other);
    }

    public boolean isLobby() {
        return isState(GameStateEnum.LOBBY);
    }

    public boolean isLastRound() {
        return Objects.nonNull(currentRound) && currentRound.getId().equals(numberOfSongs);
    }
}
