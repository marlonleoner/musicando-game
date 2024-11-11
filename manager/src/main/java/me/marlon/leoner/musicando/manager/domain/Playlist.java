package me.marlon.leoner.musicando.manager.domain;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.dto.PlaylistDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@NoArgsConstructor
public class Playlist {

    @Id
    private String id;

    private String appleMusicId;

    private String name;

    private String description;

    private String thumbnail;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @DBRef
    private List<Song> songs;

    public Playlist(String name, String description, String thumbnail) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public PlaylistDTO toDTO() {
        return toDTO(false);
    }

    public PlaylistDTO toDTO(boolean showSongs) {
        boolean songsExits = Objects.nonNull(songs);
        PlaylistDTO dto = new PlaylistDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setDescription(description);
        dto.setThumbnail(thumbnail);
        dto.setTotalSongs(songsExits ? songs.size() : 0);
        dto.setSongs(songsExits && showSongs ? songs.stream().map(Song::toDTO).toList() : null);

        return dto;
    }

    public void addSong(Song song) {
        if (Objects.isNull(songs)) {
            songs = new ArrayList<>();
        }

        songs.add(song);
    }

    public boolean containsSong(Song song) {
        return Objects.nonNull(songs) && songs.contains(song);
    }
}
