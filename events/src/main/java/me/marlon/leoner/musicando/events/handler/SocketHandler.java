package me.marlon.leoner.musicando.events.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import me.marlon.leoner.musicando.events.service.SocketService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketHandler implements WebSocketHandler {

    private final SocketService socketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        ConnectionSocket params = new ConnectionSocket(session);
        socketService.connect(session, params);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        ConnectionSocket params = new ConnectionSocket(session);
        socketService.onMessage(params, String.valueOf(message.getPayload()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("handleTransportError {}", session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        ConnectionSocket params = new ConnectionSocket(session);
        socketService.disconnect(session, closeStatus, params);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
