package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientDisconnectHandler extends AbstractHandler {

    @Override
    public void handle(Event event) throws AbstractException {
        ConnectionSocket connection = converter.deserialize(event.getObject(), ConnectionSocket.class);
        Game game = aggregation.getGameOrException(event.getGameCode());
        Player player = aggregation.getPlayerOrException(game, connection.getId());
        aggregation.onClientDisconnect(game, player);
    }
}