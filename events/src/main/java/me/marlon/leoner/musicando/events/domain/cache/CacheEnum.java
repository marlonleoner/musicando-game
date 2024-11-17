package me.marlon.leoner.musicando.events.domain.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum CacheEnum {

    GAME("MSC:GAME:%s", 60),
    PLAYER("MSC:PLAYER:%s", 60);

    private final String key;

    private final Integer expireIn;

    public String getKey(String... params) {
        return String.format(key, params);
    }
}
