package me.marlon.leoner.musicando.events.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontEvent {

    private String type;

    private Object object;
}
