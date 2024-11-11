package me.marlon.leoner.musicando.events.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractException extends Exception {

    private final String id;

    protected AbstractException(String message) {
        super(message);
        this.id = this.getClass().getSimpleName().replace("Exception", "Error");
    }
}