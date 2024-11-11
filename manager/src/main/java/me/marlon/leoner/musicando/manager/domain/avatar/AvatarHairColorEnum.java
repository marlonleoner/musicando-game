package me.marlon.leoner.musicando.manager.domain.avatar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AvatarHairColorEnum {

    AUBURN("Auburn"),
    BLACK("Black"),
    BLONDE("Blonde"),
    BLONDE_GOLDEN("BlondeGolden"),
    BROWN("Brown"),
    BROWN_DARK("BrownDark"),
    PASTEL_PINK("PastelPink"),
    BLUE("Blue"),
    PLATINUM("Platinum"),
    RED("Red"),
    SILVER_GRAY("SilverGray");

    private final String value;

    public static AvatarHairColorEnum getRandom(Random random) {
        return AvatarHairColorEnum.values()[random.nextInt(AvatarHairColorEnum.values().length)];
    }
}
