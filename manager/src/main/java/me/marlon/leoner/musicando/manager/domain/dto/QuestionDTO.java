package me.marlon.leoner.musicando.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Song;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private SongDTO answer;

    private List<SongDTO> alternatives;
}
