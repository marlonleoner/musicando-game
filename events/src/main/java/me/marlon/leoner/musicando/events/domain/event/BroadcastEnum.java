package me.marlon.leoner.musicando.events.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BroadcastEnum {
    // Both
    WELCOME("welcome"),
    // Host
    PLAYER_NEW("player/new"),
    PLAYER_UPDATE("player/update"),
    PLAYER_REMOVE("player/remove"),
    //
    //
    //
    GAME_UPDATE("game/update"),
    MATCH_UPDATE("match/update"),
    MATCH_RESULT("match/result"),
    ROUND_UPDATE("round/update"),
    ROUND_RESULT("round/result");

    private String type;
}
