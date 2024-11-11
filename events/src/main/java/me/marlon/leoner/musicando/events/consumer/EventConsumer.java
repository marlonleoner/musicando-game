package me.marlon.leoner.musicando.events.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.event.EventTypeEnum;
import me.marlon.leoner.musicando.events.handler.event.AbstractHandler;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventConsumer {

    private final ApplicationContext context;

    private final JsonConverter converter;

    @RabbitListener(queues = {Constants.EVENTS_QUEUE})
    public void consumer(String message) {
        Event event = converter.deserialize(message, Event.class);
        log.debug("Event consumer processing event {}...", event);

        EventTypeEnum eventType = EventTypeEnum.fromCode(event.getType());
        if (Objects.nonNull(eventType)) {
            AbstractHandler handler = context.getBean(eventType.getHandler());
            handler.onHandle(event);
        } else {
            log.debug("Event consumer didnt identify the event '{}'", event.getType());
        }
    }
}
