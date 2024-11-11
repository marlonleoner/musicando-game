package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GameRepository extends MongoRepository<Game, String> {

    @Query(value = "{ players: { $elemMatch: { vip: true } } }")
    boolean checkForVipPlayer(String code);
}
