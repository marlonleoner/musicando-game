package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.aggregation.GameAggregation;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.service.GameService;
import me.marlon.leoner.musicando.events.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractHandler {

    @Autowired
    protected JsonConverter converter;

    @Autowired
    protected SocketService socketService;

    @Autowired
    protected GameAggregation aggregation;

    protected abstract void handle(Event event) throws AbstractException;

    public void onHandle(Event event) {
        try {
            handle(event);
        } catch (Exception ex) {
            socketService.disconnectClient(event.getSessionId(), ex.getMessage());
            log.error("An error occurred while processing event {} in game {} - reason {}", event.getType(), event.getGameCode(), ex.getMessage());
            ex.printStackTrace();
        }
    }
}
