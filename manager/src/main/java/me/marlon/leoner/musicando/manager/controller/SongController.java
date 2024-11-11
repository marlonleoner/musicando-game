package me.marlon.leoner.musicando.manager.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Song;
import me.marlon.leoner.musicando.manager.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService service;

    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        return ResponseEntity.ok(service.findAllSongs());
    }

    @PostMapping
    public ResponseEntity<Void> createSong() {
        return ResponseEntity.created(URI.create("http://localhost")).build();
    }
}
