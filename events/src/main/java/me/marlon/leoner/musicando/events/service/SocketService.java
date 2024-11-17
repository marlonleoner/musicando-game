package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
import me.marlon.leoner.musicando.events.domain.event.BroadcastEnum;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.event.FrontEvent;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocketService {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final ApplicationContext context;

    private final JsonConverter converter;

    private final RabbitService rabbitService;

    public void connect(WebSocketSession session, ConnectionSocket connection) {
        insertSession(session);
        Event event = new Event(connection.getSessionId(), connection);
        rabbitService.sendMessage(Constants.EVENTS_QUEUE, event);
        log.info("Connecting session {} in game {}...", connection.getSessionId(), connection.getGameId());
    }

    public void onMessage(ConnectionSocket connection, String message) {
        log.debug("onMessage {}", message);
        Event event = converter.deserialize(message, Event.class);
        event.setSessionId(connection.getSessionId());
        event.setGameId(connection.getGameId());
        event.setPlayerId(connection.getId());
        event.setRole(connection.getRole().getDescription());

        rabbitService.sendMessage(Constants.EVENTS_QUEUE, event);
    }

    public void disconnect(WebSocketSession session, CloseStatus closeStatus, ConnectionSocket connection) {
        removeSession(session);
        Event event = Event.instanceDisconnectEvent(connection);
        rabbitService.sendMessage(Constants.EVENTS_QUEUE, event);
        log.info("Client {} disconnect from game {} - status {}", connection.getSessionId(), connection.getGameId(), closeStatus);
    }

    private void insertSession(WebSocketSession session) {
        sessions.add(session);
    }

    private void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    private WebSocketSession findSessionById(String sessionId) {
        return sessions.stream().filter(session -> session.getId().equals(sessionId)).findFirst().orElse(null);
    }

    private void sendMessage(WebSocketSession session, BroadcastEnum context, Object object) {
        try {
            FrontEvent payload = new FrontEvent(context.getType(), object);
            String value = converter.serialize(payload);
            TextMessage message = new TextMessage(value);
            session.sendMessage(message);
            log.debug("Message sent to client {}: {}", session.getId(), value);
        } catch (Exception ex) {
            log.error("Failed to send message to client {}: {}", session.getId(), ex.getMessage());
        }
    }

    public void broadcast(BroadcastEnum context, String sessionId, Object object) {
        WebSocketSession session = findSessionById(sessionId);
        sendMessage(session, context, object);
    }

    public void broadcastAll(BroadcastEnum context, List<String> sessionsId, Object object) {
        for (String sessionId : sessionsId) {
            broadcast(context, sessionId, object);
        }
    }

    public void disconnectClient(String sessionId, String reason) {
        try {
            WebSocketSession session = findSessionById(sessionId);
            if (Objects.nonNull(session)) session.close(new CloseStatus(CloseStatus.NORMAL.getCode(), reason));
        } catch (Exception ex) {
            log.error("failed to disconnect client {}: {}", sessionId, ex.getMessage());
        }
    }
}
