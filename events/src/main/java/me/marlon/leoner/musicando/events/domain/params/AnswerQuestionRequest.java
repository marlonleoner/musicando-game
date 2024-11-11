package me.marlon.leoner.musicando.events.domain.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerQuestionRequest {

    private String playerId;

    private String answerId;
}
