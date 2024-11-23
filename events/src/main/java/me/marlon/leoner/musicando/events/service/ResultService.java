package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.RoundResult;
import me.marlon.leoner.musicando.events.domain.game.MatchResult;
import me.marlon.leoner.musicando.events.domain.game.dto.MatchResultDTO;
import me.marlon.leoner.musicando.events.repository.RoundResultRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final RoundResultRepository repository;

    private RoundResult save(RoundResult result) {
        return repository.save(result);
    }

    public Optional<RoundResult> findByRoundIdAndPlayerId(String roundId, String playerId) {
        return repository.findByRoundIdAndPlayerId(roundId, playerId);
    }

    public List<RoundResult> findAllByRoundId(String roundId) {
        return repository.findAllByRoundId(roundId);
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

    public RoundResult onRoundResult(String roundId, String playerId) {
        Optional<RoundResult> result = findByRoundIdAndPlayerId(roundId, playerId);
        return result.orElseGet(() -> save(new RoundResult(roundId, playerId)));
    }

    public List<MatchResultDTO> onMatchResult(List<String> rounds) {
        return getOrderedMatchResult(rounds).stream().map(result -> {
            MatchResultDTO dto = new MatchResultDTO();
            dto.setPlayerId(result.getPlayerId());
            dto.setPosition(result.getPosition());
            dto.setTotalPoints(result.getTotalPoints());
            dto.setTotalCorrectAnswers(result.getCorrectAnswers());
            dto.setAverageGuessTime(result.getTotalGuessTime() / rounds.size());

            return dto;
        }).toList();
    }
}
