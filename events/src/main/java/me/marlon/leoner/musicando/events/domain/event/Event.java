package me.marlon.leoner.musicando.events.domain.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Round;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;

@Data
@NoArgsConstructor
public class Event {

    private String sessionId;

    private String type;

    private String gameCode;

    private String playerId;

    private String role;

    private Object object;

    public Event(String sessionId, ConnectionSocket connection) {
        String connectionRole = connection.getRole().getDescription();
        String eventType = connectionRole.concat("/connection");

        this.sessionId = sessionId;
        this.type = eventType;
        this.gameCode = connection.getGameCode();
        this.playerId = sessionId;
        this.role = connectionRole;
        this.object = connection;
    }

    public static Event instanceRoundPreLiveEvent(String gameCode) {
        Event event = new Event();
        event.setGameCode(gameCode);
        event.setType(EventTypeEnum.ROUND_PRE_LIVE.getType());

        return event;
    }

    public static Event instanceRoundLiveEvent(String gameCode, Round round) {
        Event event = new Event();
        event.setGameCode(gameCode);
        event.setType(EventTypeEnum.ROUND_LIVE.getType());
        event.setObject(round);

        return event;
    }

    public static Event instanceRoundFinishEvent(String gameCode, Round round) {
        Event event = new Event();
        event.setGameCode(gameCode);
        event.setType(EventTypeEnum.ROUND_FINISH.getType());
        event.setObject(round);

        return event;
    }

    public static Event instanceRoundSummaryEvent(String gameCode, Round round) {
        Event event = new Event();
        event.setGameCode(gameCode);
        event.setType(EventTypeEnum.ROUND_SUMMARY.getType());
        event.setObject(round);

        return event;
    }

    public static Event instanceGameFinishedEvent(String gameCode) {
        Event event = new Event();
        event.setGameCode(gameCode);
        event.setType(EventTypeEnum.GAME_FINISH.getType());

        return event;
    }

    public static Event instanceDisconnectEvent(ConnectionSocket connection) {
        String connectionRole = connection.getRole().getDescription();
        String eventType = connectionRole.concat("/disconnect");

        Event event = new Event();
        event.setSessionId(connection.getSessionId());
        event.setType(eventType);
        event.setGameCode(connection.getGameCode());
        event.setRole(connectionRole);
        event.setObject(connection);

        return event;
    }
}
