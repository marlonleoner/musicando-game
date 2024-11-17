package me.marlon.leoner.musicando.events.handler.event;

import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.events.domain.event.Event;
import me.marlon.leoner.musicando.events.domain.exception.AbstractException;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Match;
import me.marlon.leoner.musicando.events.domain.game.Player;
import me.marlon.leoner.musicando.events.domain.params.AnswerQuestionRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientAnswerHandler extends AbstractHandler {

    @Override
    protected void handle(Event event) throws AbstractException {
        Game game = aggregation.getGameOrException(event.getGameId());
        Match match = aggregation.getMatchOrException(game.getCurrentMatchId());
        AnswerQuestionRequest answer = converter.deserialize(event.getObject(), AnswerQuestionRequest.class);
        Player player = aggregation.getPlayerOrException(answer.getPlayerId());
        aggregation.onClientAnswer(game, match, player, answer.getAnswerId());
    }
}
