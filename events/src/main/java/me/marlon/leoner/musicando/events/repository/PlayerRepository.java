package me.marlon.leoner.musicando.events.repository;

import me.marlon.leoner.musicando.events.domain.game.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {

    List<Player> findByGameId(String gameId);

    Optional<Player> findPlayerByGameIdAndConnectedAndVip(String gameId, boolean connected, boolean vip);

    Optional<Player> findPlayerByGameIdAndConnected(String gameId, boolean connected);
}
