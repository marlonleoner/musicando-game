package me.marlon.leoner.musicando.events.domain.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateQuestionsRequest {

    private String playlistId;

    private Integer amount;

    public CreateQuestionsRequest(RequestStartParams request) {
        this.playlistId = request.getPlaylist().getId();
        this.amount = request.getAmount();
    }
}
