package me.marlon.leoner.musicando.manager.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.converter.SerializerConverter;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.apple.music.ApplePlaylist;
import me.marlon.leoner.musicando.manager.domain.params.ImportSongParams;
import me.marlon.leoner.musicando.manager.service.AppleMusicService;
import me.marlon.leoner.musicando.manager.service.PlaylistService;
import me.marlon.leoner.musicando.manager.service.RabbitService;
import me.marlon.leoner.musicando.manager.util.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportPlaylistSongsConsumer {

    private final PlaylistService service;

    private final AppleMusicService appleMusicService;

    private final RabbitService rabbitService;

    private final SerializerConverter converter;

    @RabbitListener(queues = {Constants.IMPORT_PLAYLIST_SONGS_QUEUE})
    public void receiveMessage(String message) {
        try {
            Playlist playlist = converter.deserialize(message, Playlist.class);
            log.info("Importing songs for playlist '{}' [{} - {}]...", playlist.getName(), playlist.getId(), playlist.getAppleMusicId());

            ApplePlaylist applePlaylist = appleMusicService.findPlaylistById(playlist.getAppleMusicId());
            applePlaylist.getSongs().forEach(song -> rabbitService.sendMessage(Constants.IMPORT_SONGS_QUEUE, new ImportSongParams(playlist.getId(), song)));
        } catch (Exception ex) {
            log.error("Failed to import songs for playlist: {}", ex.getMessage());
        }
    }
}
