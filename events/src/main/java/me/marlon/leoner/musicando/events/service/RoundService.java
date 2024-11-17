package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.game.*;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoundService {

    private final RedisService redisService;

    private final RoundRepository repository;

    private final ManagerIntegration managerIntegration;

    private Round save(Round round) {
        return repository.save(round);
    }

    private List<Round> saveAll(List<Round> questions) {
        return repository.saveAll(questions);
    }

    public void remove(String gameId) {
        repository.deleteAllByMatchId(gameId);
    }

    public List<String> createAndSaveRounds(Match match, RequestStartParams request) {
        List<Question> questions = managerIntegration.generateQuestions(request);

        List<Round> rounds = IntStream.range(0, questions.size()).mapToObj(index -> {
            Question question = questions.get(index);
            return new Round(match.getId(), index + 1, question);
        }).toList();

        return saveAll(rounds).stream().map(Round::getId).toList();
    }

    public Round getRound(String roundId) {
        return repository.findById(roundId).orElse(null);
    }

    public void saveResult(Game game, Round round, RoundResult result) {

    }

    public RoundResult getResultByRoundAndPlayer(String gameCode, Integer roundNumber, String playerId) {
        return null;
    }

    public Round onPreLive(String roundId) {
        Round round = getRound(roundId);
        round.setState(RoundStateEnum.PRE_LIVE);

        return save(round);
    }

    public Round onLive(String roundId) {
        Round round = getRound(roundId);
        round.setState(RoundStateEnum.LIVE);
        round.setStartedAt(System.currentTimeMillis());

        return save(round);
    }

    public Round onFinish(String roundId) {
        Round round = getRound(roundId);
        round.setState(RoundStateEnum.FINISHED);
        round.setFinishedAt(System.currentTimeMillis());

        return save(round);
    }

    public Round onSummary(String roundId) {
        Round round = getRound(roundId);
        round.setState(RoundStateEnum.SUMMARY);

        return save(round);
    }
}
