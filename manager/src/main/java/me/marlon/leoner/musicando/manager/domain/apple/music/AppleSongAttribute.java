package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class AppleSongAttribute {

    private Long durationInMillis;

    private String name;

    private String artistName;

    private AppleArtwork artwork;

    private List<AppleSongPreview> previews;

    public String getThumbnail() {
        return Objects.isNull(artwork) ? null : artwork.getThumbnail();
    }

    public String getUrlPreview() {
        return Objects.isNull(previews) ? null : previews.getFirst().getUrl();
    }
}
