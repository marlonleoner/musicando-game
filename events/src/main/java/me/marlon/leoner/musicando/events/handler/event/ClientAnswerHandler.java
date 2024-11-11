package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.exception.EventException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.game.Round;
import me.marlon.leoner.musicando.events.domain.params.AnswerQuestionRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class ClientAnswerHandler extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        AnswerQuestionRequest answer = converter.deserialize(event.getObject(), AnswerQuestionRequest.class);

        Game game = aggregation.getGameOrException(event.getGameCode());

        Player player = aggregation.getPlayerOrException(game, answer.getPlayerId());

        Round round = game.getCurrentRound();
        if (Objects.isNull(round) || !round.isLive()) {
            throw new EventException("round time is over");
        }

        aggregation.onClientAnswer(game, player, answer.getAnswerId());
    }
}
