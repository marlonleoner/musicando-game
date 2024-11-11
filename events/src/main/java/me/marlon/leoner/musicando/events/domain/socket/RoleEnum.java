package me.marlon.leoner.musicando.events.domain.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    HOST("host"),
    PLAYER("client");

    private final String description;

    public static RoleEnum get(String role) {
        for (RoleEnum item : RoleEnum.values()) {
            if (item.getDescription().equalsIgnoreCase(role)) {
                return item;
            }
        }

        return HOST;
    }
}
