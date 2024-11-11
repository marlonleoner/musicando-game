package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
