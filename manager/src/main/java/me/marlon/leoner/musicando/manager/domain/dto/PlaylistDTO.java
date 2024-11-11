package me.marlon.leoner.musicando.manager.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistDTO {

    private String id;

    private String name;

    private String description;

    private String thumbnail;

    @JsonProperty("total_songs")
    private Integer totalSongs;

    private List<SongDTO> songs;

    public PlaylistDTO(String id, String name, String thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }
}
