package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {

    List<Player> findByGame(Game game);

    Optional<Player> findPlayerByGameAndConnectedAndVip(Game game, boolean connected, boolean vip);

    Optional<Player> findPlayerByGameAndConnected(Game game, boolean connected);
}
