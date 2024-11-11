package me.marlon.leoner.musicando.manager.handler;


import me.marlon.leoner.musicando.manager.domain.Error;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
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
        ex.printStackTrace();
        return new Error("InternalError", Collections.singletonList(ex.getMessage()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new Error(ex.getId(), Collections.singletonList(ex.getMessage()));
    }
}
