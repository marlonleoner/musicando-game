package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.dto.SongDTO;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Song {

    @Id
    private String id;

    private String name;

    private String artist;

    private String preview;

    private String thumbnail;

    public SongDTO toDTO() {
        return new SongDTO(id, name, artist, thumbnail);
    }
}
