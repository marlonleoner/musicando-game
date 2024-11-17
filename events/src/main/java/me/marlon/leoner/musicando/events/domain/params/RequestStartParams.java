package me.marlon.leoner.musicando.events.domain.params;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestStartParams {

    private Integer timer;

    private Integer amount;

    private String playlistId;
}
