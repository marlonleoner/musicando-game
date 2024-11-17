package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientUpdateAvatar extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        String avatar = converter.deserialize(event.getObject(), String.class);
        Game game = aggregation.getGameOrException(event.getGameId());
        Player player = aggregation.getPlayerOrException(event.getPlayerId());
        aggregation.onClientUpdateAvatar(player, avatar);
    }
}
