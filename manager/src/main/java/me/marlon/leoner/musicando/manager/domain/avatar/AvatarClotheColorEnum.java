package me.marlon.leoner.musicando.manager.domain.avatar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AvatarClotheColorEnum {

    BLACK("Black"),
    BLUE("Blue02"),
    GRAY("Gray01"),
    PINK("Pink"),
    RED("Red"),
    WHITE("White");

    private final String value;

    public static AvatarClotheColorEnum getRandom(Random random) {
        return AvatarClotheColorEnum.values()[random.nextInt(AvatarClotheColorEnum.values().length)];
    }
}
