package me.marlon.leoner.musicando.manager.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.apple.music.ApplePlaylist;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.manager.service.AppleMusicService;
import me.marlon.leoner.musicando.manager.service.PlaylistService;
import me.marlon.leoner.musicando.manager.service.RabbitService;
import me.marlon.leoner.musicando.manager.util.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportPlaylistConsumer {

    private final PlaylistService service;

    private final AppleMusicService appleMusicService;

    private final RabbitService rabbitService;

    @RabbitListener(queues = {Constants.IMPORT_PLAYLIST_QUEUE})
    public void receiveMessage(@Payload String playlistId) {
        try {
            log.info("Importing playlist {}...", playlistId);
            ApplePlaylist applePlaylist = appleMusicService.findPlaylistById(playlistId);
            if (Objects.isNull(applePlaylist)) {
                throw new ObjectNotFoundException("Playlist not found in AppleMusic");
            }

            Playlist playlist = service.findPlaylistByAppleMusicId(playlistId);
            if (Objects.isNull(playlist)) {
                playlist = service.save(applePlaylist.toEntity());
                log.info("Playlist '{}' [{}] imported successfully", playlist.getName(), playlist.getId());
            }

            rabbitService.sendMessage(Constants.IMPORT_PLAYLIST_SONGS_QUEUE, playlist);
        } catch (Exception ex) {
            log.error("Failed to import playlist: {}", ex.getMessage());
        }
    }
}
