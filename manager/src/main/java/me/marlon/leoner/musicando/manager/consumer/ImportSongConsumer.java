package me.marlon.leoner.musicando.manager.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.converter.SerializerConverter;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.Song;
import me.marlon.leoner.musicando.manager.domain.apple.music.AppleSong;
import me.marlon.leoner.musicando.manager.domain.params.ImportSongParams;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.manager.service.PlaylistService;
import me.marlon.leoner.musicando.manager.service.SongService;
import me.marlon.leoner.musicando.manager.util.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImportSongConsumer {

    private final SerializerConverter converter;

    private final PlaylistService playlistService;

    private final SongService songService;

    @RabbitListener(queues = {Constants.IMPORT_SONGS_QUEUE})
    public void receiveMessage(String message) {
        try {
            ImportSongParams params = converter.deserialize(message, ImportSongParams.class);
            String playlistId = params.getPlaylistId();
            AppleSong appleSong = params.getSong();
            log.info("P[{}] - Importing song {} [{}]", playlistId, appleSong.getName(), appleSong.getId());

            Playlist playlist = playlistService.findPlaylistById(playlistId);
            if (Objects.isNull(playlist)) {
                log.error("P[{}] - Playlist not found", playlistId);
                throw new ObjectNotFoundException("Playlist not found");
            }

            Song song = songService.findSongByAppleMusicId(appleSong.getId());
            if (Objects.isNull(song)) {
                song = songService.save(appleSong.toEntity());
                log.info("P[{}] - Created new song '{}' [{}]", playlistId, song.getName(), song.getId());
            }

            boolean playlistContainsSong = playlist.containsSong(song);
            if (!playlistContainsSong) {
                playlist.addSong(song);
                playlistService.save(playlist);
                log.info("P[{}] - Song added to playlist '{}' [{}]", playlistId, song.getName(), song.getId());
            }
        } catch (Exception ex) {
            log.error("Failed to import songs for playlist: {}", ex.getMessage());
        }
    }
}
