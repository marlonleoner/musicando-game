package me.marlon.leoner.musicando.events.domain.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.dto.RoundDTO;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Round {

    private Integer id;

    private RoundStateEnum state;

    private Long startedAt;

    private Song answer;

    private List<Song> alternatives;

    public Round(Integer currentRound, Question question) {
        this.id = currentRound;
        this.state = RoundStateEnum.PRE_LIVE;
        this.answer = question.getAnswer();
        this.alternatives = question.getAlternatives();
    }

    @JsonIgnore
    public boolean isLive() {
        return Objects.nonNull(state) && RoundStateEnum.LIVE.equals(state);
    }

    public RoundDTO toLiveRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(id);
        dto.setState(state);
        dto.setPreview(answer.getPreview());
        dto.setAlternatives(alternatives.stream().map(Song::toDTO).toList());

        return dto;
    }

    public RoundDTO toFinishedRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(id);
        dto.setState(state);
        dto.setAnswer(answer.toDTO());
        dto.setAlternatives(alternatives.stream().map(Song::toDTO).toList());

        return dto;
    }

    public RoundDTO toSummaryRound() {
        RoundDTO dto = new RoundDTO();
        dto.setId(id);
        dto.setState(state);
        dto.setAnswer(answer.toDTO());

        return dto;
    }
}
