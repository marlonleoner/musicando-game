package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.dto.PlaylistDTO;
import me.marlon.leoner.musicando.manager.domain.params.CreatePlaylistParams;
import me.marlon.leoner.musicando.manager.domain.params.ImportPlaylistParams;
import me.marlon.leoner.musicando.manager.repository.PlaylistRepository;
import me.marlon.leoner.musicando.manager.util.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository repository;

    private final AppleMusicService appleMusicService;

    private final RabbitService rabbitService;

    public List<Playlist> getPlaylists(String query) {
        return Objects.isNull(query) ? repository.findAll() : appleMusicService.searchPlaylists(query);
    }

    public Playlist findPlaylistById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Playlist findPlaylistByAppleMusicId(String appleMusicId) {
        return repository.findByAppleMusicId(appleMusicId).orElse(null);
    }

    public Playlist createPlaylist(CreatePlaylistParams params) {
        return save(params.toEntity());
    }

    public void importPlaylist(ImportPlaylistParams params) {
        rabbitService.sendMessage(Constants.IMPORT_PLAYLIST_QUEUE, params.getPlaylistId());
    }

    public Playlist save(Playlist playlist) {
        return repository.save(playlist);
    }
}
