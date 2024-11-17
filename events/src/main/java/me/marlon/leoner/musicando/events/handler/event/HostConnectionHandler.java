package me.marlon.leoner.musicando.events.handler.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.socket.ConnectionSocket;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HostConnectionHandler extends AbstractHandler {

    @Override
    public void handle(Event event) throws AbstractException {
        ConnectionSocket params = converter.deserialize(event.getObject(), ConnectionSocket.class);

        Game game = aggregation.getGameOrException(event.getGameId());
        if (params.isReconnect()) {
            if (!game.isSecretValid(params.getSecret())) throw new EventException("invalid game secret");
            aggregation.onHostReconnect(game, params);
        } else {
            if (game.isConfigured()) throw new EventException("game already hosted");
            aggregation.onHostConnect(game, params);
        }
    }
}