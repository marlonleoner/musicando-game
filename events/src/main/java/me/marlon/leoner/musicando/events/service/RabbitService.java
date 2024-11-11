package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.converter.JsonConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitService {

    private final RabbitTemplate template;

    private final JsonConverter converter;

    public void sendMessage(String queue, Object object) {
        template.convertAndSend(queue, object);
    }
}
