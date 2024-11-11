package me.marlon.leoner.musicando.manager.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.apple.music.ApplePlaylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplePlaylistSearchResources {

    private Map<String, ApplePlaylist> playlists;

    public List<Playlist> getPlaylists() {
        return Objects.isNull(playlists) ? new ArrayList<>() : playlists.values().stream().map(ApplePlaylist::toEntity).toList();
    }
}
