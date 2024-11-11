package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Round;
import me.marlon.leoner.musicando.events.domain.game.RoundResult;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ResultService {

    private final RedisService redisService;

    private String getKey(String gameCode, String playerId) {
        return String.format(Constants.RESULTS_KEY, gameCode, playerId).toUpperCase();
    }

    public void saveResult(Game game, Round round, RoundResult result) {
        String key = getKey(game.getCode(), result.getPlayerId());
//        redisService.setItemInHash(key, String.valueOf(round.getId()), result);
    }

    public RoundResult getResultByRoundAndPlayer(String gameCode, Integer roundNumber, String playerId) {
        String key = getKey(gameCode, playerId);
//        return redisService.getItemInHash(key, String.valueOf(roundNumber), RoundResult.class);
        return null;
    }
}
