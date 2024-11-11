package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import me.marlon.leoner.musicando.events.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerRepository repository;

    public Optional<Player> getPlayer(String playerId) {
        return repository.findById(playerId);
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    private void remove(Player player) {
        repository.delete(player);
    }

    public List<Player> getPlayers(Game game) {
        return repository.findByGame(game);
    }

    public Optional<Player> getVipPlayer(Game game) {
        return repository.findPlayerByGameAndConnectedAndVip(game, true, true);
    }

    public boolean checkForVipPlayer(Game game) {
        Optional<Player> vip = getVipPlayer(game);
        return vip.isPresent();
    }

    public Optional<Player> getNextVipPlayer(Game game) {
        return repository.findPlayerByGameAndConnected(game, true);
    }

    public Player onPlayerConnect(Game game, ConnectionSocket connection) {
        Player player = new Player();
        player.setId(connection.getSessionId());
        player.setSessionId(connection.getSessionId());
        player.setSecret(UUID.randomUUID().toString());
        player.setName(connection.getName());
        player.setAvatar(connection.getAvatar());
        player.setConnected(true);
        player.setVip(!checkForVipPlayer(game));
        player.setGame(game);
        player.setPoints(0);
        player.setRoundsCorrects(0);

        return save(player);
    }

    public void onPlayerReconnect(Game game, Player player, ConnectionSocket connection) {
        player.setSessionId(connection.getSessionId());
        player.setConnected(true);
        player.setVip(!checkForVipPlayer(game));
        save(player);
    }

    public void onPlayerDisconnect(Player player) {
        player.setConnected(false);
        player.setVip(false);
        save(player);
    }

    public void onPlayerDestroy(Game game, Player player) {
        remove(player);
    }

    public void onPlayerUpdateAvatar(Player player, String avatar) {
        player.setAvatar(avatar);
        save(player);
    }
}
