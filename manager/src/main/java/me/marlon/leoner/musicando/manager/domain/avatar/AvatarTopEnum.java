package me.marlon.leoner.musicando.manager.domain.avatar;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum AvatarTopEnum {

    NO_HAIR("NoHair"),
    HAT("Hat"),
    WINTER_HAT("WinterHat3"),
    LONG_HAIR_BIG_HAIR("LongHairBigHair"),
    LONG_HAIR_BOB("LongHairBob"),
    LONG_HAIR_BUN("LongHairBun"),
    LONG_HAIR_CURLY("LongHairCurly"),
    LONG_HAIR_CURVY("LongHairCurvy"),
    LONG_HAIR_NOT_TOO_LONG("LongHairNotTooLong"),
    LONG_HAIR_SHAVED_SIDES("LongHairShavedSides"),
    LONG_HAIR_MIA_WALLACE("LongHairMiaWallace"),
    LONG_HAIR_STRAIGHT("LongHairStraight"),
    LONG_HAIR_STRAIGHT_STRAND("LongHairStraightStrand"),
    SHORT_HAIR_DREADS("ShortHairDreads01"),
    SHORT_HAIR_FRIZZLE("ShortHairFrizzle"),
    SHORT_HAIR_MULLET("ShortHairShaggyMullet"),
    SHORT_HAIR_CURLY("ShortHairShortCurly"),
    SHORT_HAIR_FLAT("ShortHairShortFlat"),
    SHORT_HAIR_ROUND("ShortHairShortRound"),
    SHORT_HAIR_WAVED("ShortHairShortWaved"),
    SHORT_HAIR_THE_CAESAR("ShortHairTheCaesar");

    private final String value;

    public static AvatarTopEnum getRandom(Random random) {
        return AvatarTopEnum.values()[new Random().nextInt(AvatarTopEnum.values().length)];
    }
}
