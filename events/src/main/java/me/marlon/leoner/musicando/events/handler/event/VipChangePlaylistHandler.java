package me.marlon.leoner.musicando.events.handler.event;

import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Playlist;
import org.springframework.stereotype.Component;

@Component
public class VipChangePlaylistHandler extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        Playlist playlist = converter.deserialize(event.getObject(), Playlist.class);
        Game game = aggregation.getGameOrException(event.getGameCode());
        aggregation.onPlaylistChange(game, playlist);
    }
}
