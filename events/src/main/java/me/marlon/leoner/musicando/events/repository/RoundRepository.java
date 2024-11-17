package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Round;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoundRepository extends MongoRepository<Round, String> {

    void deleteAllByMatchId(String matchId);
}
