package me.marlon.leoner.musicando.manager.domain.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenerateQuestionsParams {

    private String playlistId;

    private Integer amount;
}
