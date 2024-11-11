package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.converter.SerializerConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitService {

    private final RabbitTemplate rabbitTemplate;

    private final SerializerConverter converter;

    public void sendMessage(String queue, Object object) {
        try {
            sendMessage(queue, converter.serialize(object));
        } catch (Exception ex) {
            log.error("Failed to send message to queue {}: {}", queue, ex.getMessage());
        }
    }

    public void sendMessage(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
    }
}
