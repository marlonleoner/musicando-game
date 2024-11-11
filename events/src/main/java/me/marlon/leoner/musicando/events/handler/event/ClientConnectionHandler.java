package me.marlon.leoner.musicando.events.handler.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientConnectionHandler extends AbstractHandler {

    @Override
    public void handle(Event event) throws AbstractException {
        Game game = aggregation.getGameOrException(event.getGameCode());

        ConnectionSocket params = converter.deserialize(event.getObject(), ConnectionSocket.class);
        Optional<String> clientId = Optional.ofNullable(params.getClientId());
        if (clientId.isEmpty()) {
            aggregation.onClientConnect(game, params);
        } else {
            Player player = aggregation.getPlayerOrException(game, clientId.get());
            if (!player.isSecretValid(params.getSecret())) throw new EventException("invalid game secret");
            aggregation.onClientReconnect(game, player, params);
        }
    }
}
