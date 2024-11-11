package me.marlon.leoner.musicando.manager.domain.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.manager.domain.apple.music.AppleSong;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportSongParams {

    private String playlistId;

    private AppleSong song;
}
