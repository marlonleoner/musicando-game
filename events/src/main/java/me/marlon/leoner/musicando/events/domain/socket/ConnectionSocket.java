package me.marlon.leoner.musicando.events.domain.socket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionSocket {

    private String sessionId;

    private String gameCode;

    private String clientId;

    private String secret;

    private String role;

    private String name;

    private String avatar;

    public ConnectionSocket(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        this.sessionId = session.getId();
        this.gameCode = (String) attributes.getOrDefault("gameCode", null);
        this.clientId = (String) attributes.getOrDefault("id", null);
        this.secret = (String) attributes.getOrDefault("secret", null);
        this.role = (String) attributes.getOrDefault("role", null);
        this.name = (String) attributes.getOrDefault("name", null);
        this.avatar = (String) attributes.getOrDefault("avatar", null);
    }

    @JsonIgnore
    public RoleEnum getRole() {
        return RoleEnum.get(role);
    }

    @JsonIgnore
    public boolean isReconnect() {
        return Objects.nonNull(clientId) && Objects.nonNull(secret);
    }

    @JsonIgnore
    public String getId() {
        return Objects.nonNull(clientId) ? clientId : sessionId;
    }
}
