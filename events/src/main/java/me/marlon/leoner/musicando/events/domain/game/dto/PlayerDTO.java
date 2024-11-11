package me.marlon.leoner.musicando.events.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private String id;

    private String name;

    private boolean connected;

    private boolean vip;
}
