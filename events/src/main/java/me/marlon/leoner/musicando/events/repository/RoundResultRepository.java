package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.RoundResult;
import me.marlon.leoner.musicando.events.domain.game.MatchResult;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoundResultRepository extends MongoRepository<RoundResult, String> {

    Optional<RoundResult> findByRoundIdAndPlayerId(String roundId, String playerId);

    List<RoundResult> findAllByRoundId(String roundId);

    @Query("{ 'roundId': { '$in': ?0 } }")
    List<RoundResult> findAllByIds(List<String> ids);

    @Aggregation(pipeline = {
            "{ $match: { roundId: { '$in': ?0 } } }",
            "{ $group: { _id: { playerId: '$playerId' }, correctAnswers: { $sum: { $cond: ['$correct', 1, 0] } }, totalPoints: { $sum: '$points' }, totalGuessTime: { $sum: '$guessTime' } } }",
            "{ $setWindowFields:  { sortBy: { totalPoints: -1 }, output: { position: { $denseRank: {} } } } }",
            "{ $project: { _id: 0, playerId: '$_id.playerId', totalPoints: 1, totalGuessTime: 2, correctAnswers: 3, position: 4 } }"
    })
    List<MatchResult> findMatchResult(List<String> rounds);
}
