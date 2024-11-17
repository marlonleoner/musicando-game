package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Match;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VipRoundDurationHandler extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        Integer roundDuration = converter.deserialize(event.getObject(), Integer.class);
        Game game = aggregation.getGameOrException(event.getGameId());
        Match match = aggregation.getMatchOrException(game.getCurrentMatchId());
        aggregation.onRoundDurationChange(game, match, roundDuration);
    }
}
