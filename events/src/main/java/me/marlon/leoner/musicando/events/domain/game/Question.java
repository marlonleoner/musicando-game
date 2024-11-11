package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@NoArgsConstructor
public class Question {

    @Id
    private String id;

    @DBRef
    private Game game;

    private Integer roundNumber;

    @DBRef
    private Song answer;

    @DBRef
    List<Song> alternatives;
}