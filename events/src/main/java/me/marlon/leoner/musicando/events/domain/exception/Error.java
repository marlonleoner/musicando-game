package me.marlon.leoner.musicando.events.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Error {

    private String code;

    private List<String> message;
}