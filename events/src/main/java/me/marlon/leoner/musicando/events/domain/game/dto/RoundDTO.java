package me.marlon.leoner.musicando.events.domain.game.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.RoundStateEnum;

import java.util.List;

@Data
@NoArgsConstructor
public class RoundDTO {

    private Integer id;

    private RoundStateEnum state;

    private String preview;

    private SongDTO answer;

    private List<SongDTO> alternatives;
}
