package me.marlon.leoner.musicando.manager.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.Song;
import me.marlon.leoner.musicando.manager.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository repository;

    public List<Song> findAllSongs() {
        return repository.findAll();
    }

    public Song findSongById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Song findSongByAppleMusicId(Long appleMusicId) {
        return repository.findByAppleMusicId(appleMusicId).orElse(null);
    }

    public Song save(Song song) {
        return repository.save(song);
    }
}
