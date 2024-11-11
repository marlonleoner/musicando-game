package me.marlon.leoner.musicando.manager.domain;


import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.dto.SongDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Song {

    @Id
    private String id;

    private Long appleMusicId;

    private String name;

    private String artist;

    private String preview;

    private String thumbnail;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    public Song(String name, String artist, String preview, String thumbnail) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.artist = artist;
        this.preview = preview;
        this.thumbnail = thumbnail;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public SongDTO toDTO() {
        SongDTO dto = new SongDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setArtist(artist);
        dto.setPreview(preview);
        dto.setThumbnail(thumbnail);

        return dto;
    }
}
