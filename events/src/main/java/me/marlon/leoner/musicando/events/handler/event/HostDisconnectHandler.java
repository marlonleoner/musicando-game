package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Match;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HostDisconnectHandler extends AbstractHandler {

    @Override
    public void handle(Event event) throws AbstractException {
        Game game = aggregation.getGameOrException(event.getGameId());
        Match match = aggregation.getMatchOrException(game.getCurrentMatchId());
        aggregation.onHostDisconnect(game, match);
    }
}