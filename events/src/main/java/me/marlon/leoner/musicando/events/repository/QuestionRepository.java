package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuestionRepository extends MongoRepository<Question, String> {

    Optional<Question> findQuestionByGameAndRoundNumber(Game game, Integer roundNumber);

    void deleteAllByGame(Game game);
}
