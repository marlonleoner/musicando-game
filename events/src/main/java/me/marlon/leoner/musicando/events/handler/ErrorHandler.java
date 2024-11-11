package me.marlon.leoner.musicando.events.handler;

import me.marlon.leoner.musicando.events.domain.exception.Error;
import me.marlon.leoner.musicando.events.domain.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error handleGenericException(Exception ex) {
        return new Error("InternalError", Collections.singletonList(ex.getMessage()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new Error(ex.getId(), Collections.singletonList(ex.getMessage()));
    }
}
