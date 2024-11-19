package me.marlon.leoner.musicando.events.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Avatar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private String id;

    private String name;

    private boolean connected;

    private boolean vip;

    private Avatar avatar;
}
