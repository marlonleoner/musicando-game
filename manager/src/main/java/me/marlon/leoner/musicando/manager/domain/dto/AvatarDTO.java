package me.marlon.leoner.musicando.manager.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AvatarDTO {

    private String style = "Transparent";

    private String accessories = "Blank";

    private String facialHair = "Blank";

    private String eye = "Default";

    private String eyebrow = "Default";

    private String mouth = "Twinkle";

    private String top;

    private String hairColor;

    private String clothe;

    private String clotheColor;

    private String skinColor;

    public AvatarDTO(String top, String hairColor, String clothe, String clotheColor, String skinColor) {
        this.top = top;
        this.hairColor = hairColor;
        this.clothe = clothe;
        this.clotheColor = clotheColor;
        this.skinColor = skinColor;
    }
}
