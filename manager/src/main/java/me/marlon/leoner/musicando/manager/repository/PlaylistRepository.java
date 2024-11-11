package me.marlon.leoner.musicando.manager.repository;

import me.marlon.leoner.musicando.manager.domain.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends MongoRepository<Playlist, String> {

    Optional<Playlist> findByAppleMusicId(String appleMusicId);
}
