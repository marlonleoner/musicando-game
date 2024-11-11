package me.marlon.leoner.musicando.events.domain.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Playlist {

    private String id;

    private String name;

    private String description;

    private String thumbnail;

    @JsonIgnore
    private List<Song> songs;

    @JsonProperty("total_songs")
    public Integer getTotalSongs() {
        return songs.size();
    }
}