package me.marlon.leoner.musicando.manager.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SongDTO {

    private String id;

    private String name;

    private String artist;

    private String preview;

    private String thumbnail;
}
