package me.marlon.leoner.musicando.events.domain.game;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.dto.RoundDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document("rounds")
public class Round {

    @Id
    private String id;

    private String matchId;

    private Integer roundNumber;

    private RoundStateEnum state;

    private Song answer;

    private List<Song> alternatives;

    private Long startedAt;

    private Long finishedAt;

    public Round(String matchId, Integer roundNumber, Question question) {
        this.id = UUID.randomUUID().toString();
        this.roundNumber = roundNumber;
        this.matchId = matchId;
        this.state = RoundStateEnum.CREATED;
        this.answer = question.getAnswer();
        this.alternatives = question.getAlternatives();
    }

    public boolean isLive() {
        return Objects.nonNull(state) && RoundStateEnum.LIVE.equals(state);
    }

    public RoundDTO toLiveRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(roundNumber);
        dto.setState(state);
        dto.setPreview(answer.getPreview());
        dto.setAlternatives(alternatives.stream().map(Song::toDTO).toList());

        return dto;
    }

    public RoundDTO toFinishedRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(roundNumber);
        dto.setState(state);
        dto.setAnswer(answer.toDTO());
        dto.setAlternatives(alternatives.stream().map(Song::toDTO).toList());

        return dto;
    }

    public RoundDTO toSummaryRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(roundNumber);
        dto.setState(state);
        dto.setAnswer(answer.toDTO());

        return dto;
    }
}
