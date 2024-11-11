package me.marlon.leoner.musicando.manager.domain.avatar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AvatarSkinEnum {

    PALE("Pale"),
    LIGHT("Light"),
    BROWN("Brown"),
    DARK_BROWN("DarkBrown");

    private final String value;

    public static AvatarSkinEnum getRandom(Random random) {
        return AvatarSkinEnum.values()[random.nextInt(AvatarSkinEnum.values().length)];
    }
}
