package me.marlon.leoner.musicando.manager.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.aggregation.PlaylistAggregation;
import me.marlon.leoner.musicando.manager.domain.dto.PlaylistDTO;
import me.marlon.leoner.musicando.manager.domain.params.CreatePlaylistParams;
import me.marlon.leoner.musicando.manager.domain.params.ImportPlaylistParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistAggregation aggregation;

    @GetMapping
    public ResponseEntity<List<PlaylistDTO>> getPlaylists(@RequestParam(value = "q", required = false) String query) {
        return ResponseEntity.ok(aggregation.getPlaylists(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylist(@PathVariable("id") String id) {
        return ResponseEntity.ok(aggregation.findPlaylistById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody CreatePlaylistParams params) {
        PlaylistDTO playlist = aggregation.createPlaylist(params);
        return ResponseEntity.created(URI.create("/playlists/".concat(playlist.getId()))).build();
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importPlaylist(@RequestBody ImportPlaylistParams params) {
        aggregation.importPlaylist(params);
        return ResponseEntity.noContent().build();
    }
}
