package me.marlon.leoner.musicando.events.domain.game;

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

    private List<Song> songs;
}