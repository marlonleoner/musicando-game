package me.marlon.leoner.musicando.manager.aggregation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.dto.PlaylistDTO;
import me.marlon.leoner.musicando.manager.domain.params.CreatePlaylistParams;
import me.marlon.leoner.musicando.manager.domain.params.ImportPlaylistParams;
import me.marlon.leoner.musicando.manager.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaylistAggregation {

    private final PlaylistService service;

    public List<PlaylistDTO> getPlaylists(String query) {
        List<Playlist> playlists = service.getPlaylists(query);
        return playlists.stream().map(Playlist::toDTO).toList();
    }

    public PlaylistDTO findPlaylistById(String id) {
        Playlist playlist = service.findPlaylistById(id);
        return Objects.isNull(playlist) ? null : playlist.toDTO(true);
    }

    public PlaylistDTO createPlaylist(CreatePlaylistParams params) {
        Playlist playlist = service.createPlaylist(params);
        return playlist.toDTO();
    }

    public void importPlaylist(ImportPlaylistParams params) {
        service.importPlaylist(params);
    }
}
