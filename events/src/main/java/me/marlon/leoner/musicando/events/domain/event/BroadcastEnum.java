package me.marlon.leoner.musicando.events.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BroadcastEnum {
    // Both
    WELCOME("welcome"),
    UPDATE_ROUND("round/update"),
    // Host
    PLAYER_NEW("player/new"),
    PLAYER_UPDATE("player/update"),
    PLAYER_REMOVE("player/remove"),
    PLAYER_ANSWER("player/answer"),
    // Client
    UPDATE_GAME("game/update"),
    ;

    private String type;
}
