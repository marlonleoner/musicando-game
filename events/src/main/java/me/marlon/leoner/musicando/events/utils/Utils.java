package me.marlon.leoner.musicando.events.utils;

import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.event.EventTypeEnum;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Round;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

import static me.marlon.leoner.musicando.events.utils.Constants.*;

public class Utils {

    private static final Random random = new Random();

    public static Integer getRandomNumber(Integer min, Integer max) {
        return Utils.random.nextInt(max - min + 1) + min;
    }

    public static String generateId() {
        return RandomStringUtils.random(4, 0, CHARS.length() - 1, false, false, CHARS.toCharArray(), new SecureRandom());
    }

    public static String getRandomAvatar() {
        return AVATARS.get(Utils.getRandomNumber(0, AVATARS.size() - 1));
    }

    public static String getRandomBackground() {
        return BACKGROUNDS.get(Utils.getRandomNumber(0, BACKGROUNDS.size() - 1));
    }
}
