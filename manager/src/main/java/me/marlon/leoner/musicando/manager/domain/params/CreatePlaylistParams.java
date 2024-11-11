package me.marlon.leoner.musicando.manager.domain.params;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Playlist;

@Data
@NoArgsConstructor
public class CreatePlaylistParams {

    private String name;

    private String description;

    private String thumbnail;

    public Playlist toEntity() {
        return new Playlist(name, description, thumbnail);
    }
}
