package me.marlon.leoner.musicando.events.domain.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientAnswerResponse {

    private String id;

    private boolean correctAnswer;

    private Integer roundPoints;
}
