package me.marlon.leoner.musicando.events.domain.params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Playlist;
import me.marlon.leoner.musicando.events.domain.game.Round;

import java.util.Objects;

@Data
@NoArgsConstructor
public class RequestStartParams {

    private Integer timer;

    private Integer amount;

    private Playlist playlist;

    @JsonIgnore
    public boolean isPlaylistValid() {
        return Objects.nonNull(playlist) && Objects.nonNull(playlist.getId());
    }
}
