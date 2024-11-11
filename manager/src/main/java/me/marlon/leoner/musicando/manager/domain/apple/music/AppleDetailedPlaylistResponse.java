package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class AppleDetailedPlaylistResponse {

    private List<ApplePlaylist> data;

    public ApplePlaylist getPlaylist() {
        return Objects.isNull(data) || data.isEmpty() ? null : data.getFirst();
    }
}
