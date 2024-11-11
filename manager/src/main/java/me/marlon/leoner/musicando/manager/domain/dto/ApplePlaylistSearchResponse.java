package me.marlon.leoner.musicando.manager.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplePlaylistSearchResponse {

    private ApplePlaylistSearchResources resources;

    public List<Playlist> getPlaylists() {
        return Objects.isNull(resources) ? new ArrayList<>() : resources.getPlaylists();
    }
}
