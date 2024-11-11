package me.marlon.leoner.musicando.events.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    protected static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    protected static final List<String> AVATARS = Arrays.asList("alligator", "anteater", "armadillo", "axolotl", "badger", "bat", "beaver", "buffalo", "camel", "capybara", "chameleon", "cheetah", "chinchilla", "chipmunk", "chupacabra", "cormorant", "coyote", "crow", "dingo", "dinosaur", "duck", "elephant", "ferret", "fox", "frog", "giraffe", "gopher", "grizzly", "hedgehog", "hippo", "hyena", "ibex", "ifrit", "iguana", "jackal", "kangaroo", "koala", "kraken", "leopard", "lemur", "liger", "manatee", "monkey", "moose", "narwhal", "orangutan", "otter", "panda", "penguin", "platypus", "pumpkin", "python", "quagga", "rabbit", "raccoon", "rhino", "sheep", "shrew", "skunk", "squirrel", "tiger", "turtle", "walrus", "wolf", "wolverine", "wombat");
    protected static final List<String> BACKGROUNDS = Arrays.asList("#FF1178", "#FE0000", "#FE8700", "#037CFE", "#00FE2E", "#9700FE");

    public static final String GAME_KEY = "MSC:%s";
    public static final String QUESTIONS_KEY = GAME_KEY.concat(":QUESTIONS");
    public static final String PLAYERS_KEY = GAME_KEY.concat(":PLAYERS");
    public static final String RESULTS_KEY = GAME_KEY.concat(":RESULTS:%s");

    public static final String EVENTS_QUEUE = "MUSICANDO-EVENTS";

    public static final Integer ROUND_LIVE_DELAY = 3;
    public static final Integer ROUND_FINISH_DELAY = 5;

    public static final Integer MAX_POINTS = 300;
}
