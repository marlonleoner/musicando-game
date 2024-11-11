package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Song;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AppleSong {

    private Long id;

    private AppleSongAttribute attributes;

    public String getName() {
        return Objects.isNull(attributes) ? null : attributes.getName().replaceAll("(?<=[(])[^)]*", "").replace("(", "").replace(")", "").trim();
    }

    public String getArtist() {
        return Objects.isNull(attributes) ? null : attributes.getArtistName();
    }

    public String getThumbnail() {
        return attributes.getThumbnail();
    }

    public String getPreview() {
        return attributes.getUrlPreview();
    }

    public Song toEntity() {
        Song song = new Song();
        song.setId(UUID.randomUUID().toString());
        song.setName(getName());
        song.setArtist(getArtist());
        song.setAppleMusicId(id);
        song.setThumbnail(getThumbnail());
        song.setPreview(getPreview());
        song.setCreatedAt(new Date());
        song.setUpdatedAt(new Date());

        return song;
    }
}
