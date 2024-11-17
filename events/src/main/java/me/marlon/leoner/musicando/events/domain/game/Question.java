package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {

    private Song answer;

    List<Song> alternatives;
}