package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VipNumberOfRoundsHandler extends AbstractHandler {


    @Override
    protected void handle(Event event) throws AbstractException {
        Integer numberOfRounds = converter.deserialize(event.getObject(), Integer.class);
        Game game = aggregation.getGameOrException(event.getGameCode());
        aggregation.onNumberOfRoundsChange(game, numberOfRounds);
    }
}
