package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@NoArgsConstructor
@Document("games")
public class Game {

    @Id
    private String id;

    private String sessionId;

    private String secret;

    private boolean connected;

    private GameStateEnum state;

    private String currentMatchId;

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

    public boolean isFinished() {
        return isState(GameStateEnum.FINISHED);
    }
}
