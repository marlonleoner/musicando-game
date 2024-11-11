package me.marlon.leoner.musicando.manager.domain.avatar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AvatarClotheEnum {

    BALZER("BlazerShirt"),
    HOODIE("Hoodie"),
    SHIRT("ShirtCrewNeck");

    private final String value;

    public static AvatarClotheEnum getRandom(Random random) {
        return AvatarClotheEnum.values()[random.nextInt(AvatarClotheEnum.values().length)];
    }
}
