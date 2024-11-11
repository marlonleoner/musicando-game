package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplePlaylistRelationships {

    private ApplePlaylistTracksRelationships tracks;

    public List<AppleSong> getSongs() {
        return Objects.isNull(tracks) ? null : tracks.getSongs();
    }

    @Data
    protected static class ApplePlaylistTracksRelationships {

        private List<AppleSong> data;

        public List<AppleSong> getSongs() {
            return data;
        }
    }
}
