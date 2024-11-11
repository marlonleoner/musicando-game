package me.marlon.leoner.musicando.events.handler.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VipStartGameHandler extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        log.debug("Starting game {}...", event);

        Game game = aggregation.getGameOrException(event.getGameCode());
        if (!game.isLobby()) {
            throw new EventException("unable to start the game without being in the lobby", true);
        }

        RequestStartParams params = converter.deserialize(event.getObject(), RequestStartParams.class);
        if (!params.isPlaylistValid()) {
            throw new EventException("playlist must be required", true);
        }

        aggregation.onStartGame(game, params);
    }
}
