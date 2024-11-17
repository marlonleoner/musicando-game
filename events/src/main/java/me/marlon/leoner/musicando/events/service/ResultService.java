package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.RoundResult;
import me.marlon.leoner.musicando.events.domain.game.dto.MatchResult;
import me.marlon.leoner.musicando.events.repository.RoundResultRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final RoundResultRepository repository;

    private RoundResult save(RoundResult result) {
        return repository.save(result);
    }

    public RoundResult findByRoundIdAndPlayerId(String roundId, String playerId) {
        return repository.findByRoundIdAndPlayerId(roundId, playerId);
    }

    public List<RoundResult> findAllByRoundId(String id) {
        return repository.findAllByRoundId(id);
    }

    public List<RoundResult> findAllByIds(List<String> ids) {
        return repository.findAllByIds(ids);
    }

    public void onAnswer(String roundId, String playerId, boolean isCorrect, Integer points, Long guessTime) {
        RoundResult result = new RoundResult();
        result.setRoundId(roundId);
        result.setPlayerId(playerId);
        result.setCorrect(isCorrect);
        result.setPoints(points);
        result.setGuessTime(guessTime);

        save(result);
    }

    public List<RoundResult> getOrderedRoundResult(String roundId) {
        List<RoundResult> results = findAllByRoundId(roundId);
        results.sort(Comparator.comparingInt(RoundResult::getPoints));
        return results;
    }

    public List<MatchResult> getOrderedMatchResult(List<String> roundsId) {
        return repository.findMatchResult(roundsId);
    }
}
