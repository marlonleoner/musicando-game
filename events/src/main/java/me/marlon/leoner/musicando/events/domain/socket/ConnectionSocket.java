package me.marlon.leoner.musicando.events.domain.socket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Avatar;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionSocket {

    private String sessionId;

    private String gameId;

    private String playerId;

    private String secret;

    private String role;

    private String name;

    private Avatar avatar;

    public ConnectionSocket(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        this.sessionId = session.getId();
        this.gameId = (String) attributes.getOrDefault("gameCode", null);
        this.playerId = (String) attributes.getOrDefault("id", null);
        this.secret = (String) attributes.getOrDefault("secret", null);
        this.role = (String) attributes.getOrDefault("role", null);
        this.name = (String) attributes.getOrDefault("name", null);
        this.avatar = new Avatar(
                (String) attributes.getOrDefault("style", null),
                (String) attributes.getOrDefault("accessories", null),
                (String) attributes.getOrDefault("facialHair", null),
                (String) attributes.getOrDefault("eye", null),
                (String) attributes.getOrDefault("eyebrow", null),
                (String) attributes.getOrDefault("mouth", null),
                (String) attributes.getOrDefault("top", null),
                (String) attributes.getOrDefault("hairColor", null),
                (String) attributes.getOrDefault("clothe", null),
                (String) attributes.getOrDefault("clotheColor", null),
                (String) attributes.getOrDefault("skinColor", null)
        );
    }

    @JsonIgnore
    public RoleEnum getRole() {
        return RoleEnum.get(role);
    }

    @JsonIgnore
    public boolean isReconnect() {
        return Objects.nonNull(playerId) && Objects.nonNull(secret);
    }

    @JsonIgnore
    public String getId() {
        if (RoleEnum.HOST.equals(getRole())) return null;

        return Objects.nonNull(playerId) ? playerId : sessionId;
    }
}
