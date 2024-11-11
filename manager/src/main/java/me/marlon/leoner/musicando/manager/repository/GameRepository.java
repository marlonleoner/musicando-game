package me.marlon.leoner.musicando.manager.repository;

import me.marlon.leoner.musicando.manager.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

}
