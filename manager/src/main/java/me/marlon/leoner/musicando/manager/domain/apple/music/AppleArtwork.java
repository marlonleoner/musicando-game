package me.marlon.leoner.musicando.manager.domain.apple.music;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
@NoArgsConstructor
public class AppleArtwork {

    private Integer width;

    private Integer height;

    private String url;

    private String textColor1;

    private String textColor2;

    private String textColor3;

    private String textColor4;

    private String bgColor;

    public String getThumbnail() {
        if (StringUtils.isBlank(url) || Objects.isNull(width) || Objects.isNull(height)) return "";
        return url.replace("{w}", width.toString()).replace("{h}", height.toString());
    }
}
