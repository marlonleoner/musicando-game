package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Playlist;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ApplePlaylist implements Serializable {

    private String id;

    private String type;

    private String href;

    private ApplePlaylistAttributes attributes;

    private ApplePlaylistRelationships relationships;

    public String getName() {
        return attributes.getName();
    }

    public String getThumbnail() {
        return attributes.getThumbnail();
    }

    public String getShortDescription() {
        return attributes.getShortDescription();
    }

    public String getFullDescription() {
        return attributes.getFullDescription();
    }

    private String getDescription() {
        String shortDescription = attributes.getShortDescription();
        return Objects.nonNull(shortDescription) ? shortDescription : getFullDescription();
    }

    public List<AppleSong> getSongs() {
        return Objects.isNull(relationships) ? null : relationships.getSongs();
    }

    public Playlist toEntity() {
        Playlist playlist = new Playlist();
        playlist.setId(UUID.randomUUID().toString());
        playlist.setAppleMusicId(id);
        playlist.setName(getName());
        playlist.setDescription(getDescription());
        playlist.setThumbnail(getThumbnail());
        playlist.setCreatedAt(new Date());
        playlist.setUpdatedAt(new Date());

        return playlist;
    }
}
