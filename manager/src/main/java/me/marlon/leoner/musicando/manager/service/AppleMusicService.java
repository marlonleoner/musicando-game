package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.apple.music.ApplePlaylist;
import me.marlon.leoner.musicando.manager.domain.dto.ApplePlaylistSearchResponse;
import me.marlon.leoner.musicando.manager.domain.apple.music.AppleDetailedPlaylistResponse;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppleMusicService {

    private static final String BASE_SEARCH_PLAYLIST_URL = "https://amp-api-edge.music.apple.com/v1/catalog/us/search?fields[artists]=url,name,artwork&format[resources]=map&l=pt-BR&limit=30&omit[resource]=autos&platform=web&types=playlists&term=%s";
    private static final String BASE_URL_GET_PLAYLIST = "https://amp-api.music.apple.com/v1/catalog/us/playlists/%s?l=pt-BR&fields=name,artwork,description,url&fields[songs]=name,artistName,artwork,url,durationInMillis,previews&limit[tracks]=300";

    private final RestClient restClient;

    private <T> T request(String url, Class<T> type) {
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(type);
    }

    public List<Playlist> searchPlaylists(String query) {
        try {
            String url = String.format(BASE_SEARCH_PLAYLIST_URL, query);
            ApplePlaylistSearchResponse response = request(url, ApplePlaylistSearchResponse.class);
            return response.getPlaylists();
        } catch (Exception ex) {
            log.error("Failed to search playlists {}: {}", query, ex.getMessage());
            throw new ObjectNotFoundException("Failed to search playlists: " + ex.getMessage());
        }
    }

    public ApplePlaylist findPlaylistById(String playlistId) throws ObjectNotFoundException {
        try {
            String url = String.format(BASE_URL_GET_PLAYLIST, playlistId);
            AppleDetailedPlaylistResponse response = request(url, AppleDetailedPlaylistResponse.class);
            if (Objects.isNull(response)) throw new ObjectNotFoundException("Failed to parse response");
            return response.getPlaylist();
        } catch (Exception ex) {
            String message = String.format("Failed to find playlist %s: %s", playlistId, ex.getMessage());
            throw new ObjectNotFoundException(message);
        }
    }
}