package me.marlon.leoner.musicando.events.domain.exception;

import lombok.Getter;

public class EventException extends AbstractException {

    @Getter
    private final boolean disconnect;

    public EventException(String message) {
        this(message, false);
    }

    public EventException(String message, boolean disconnect) {
        super(message);
        this.disconnect = disconnect;
    }
}
