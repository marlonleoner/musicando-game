package me.marlon.leoner.musicando.manager.domain.apple.music;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplePlaylistDescription {

    @JsonProperty("standard")
    private String fullDescription;

    @JsonProperty("short")
    private String shortDescription;
}
