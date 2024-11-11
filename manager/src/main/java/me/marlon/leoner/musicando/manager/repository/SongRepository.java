package me.marlon.leoner.musicando.manager.repository;

import me.marlon.leoner.musicando.manager.domain.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {

    Optional<Song> findByAppleMusicId(Long appleMusicId);
}
