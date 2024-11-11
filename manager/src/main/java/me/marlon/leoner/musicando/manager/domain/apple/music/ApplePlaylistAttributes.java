package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplePlaylistAttributes {

    private String name;

    private ApplePlaylistDescription description;

    private AppleArtwork artwork;

    private String url;

    public String getThumbnail() {
        return Objects.isNull(artwork) ? null : artwork.getThumbnail();
    }

    public String getShortDescription() {
        return Objects.isNull(description) ? null : description.getShortDescription();
    }

    public String getFullDescription() {
        return Objects.isNull(description) ? null : description.getFullDescription();
    }
}
