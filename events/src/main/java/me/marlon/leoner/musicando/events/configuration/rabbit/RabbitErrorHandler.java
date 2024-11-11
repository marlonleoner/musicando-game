package me.marlon.leoner.musicando.events.configuration.rabbit;

import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.util.ErrorHandler;

public class RabbitErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        if (!(t.getCause() instanceof AbstractException)) {
            throw new AmqpRejectAndDontRequeueException("Error Handler converted exception to fatal", t);
        }
    }
}
