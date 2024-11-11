package me.marlon.leoner.musicando.events.domain.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.dto.PlayerDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Player {

    @Id
    private String id;

    private String sessionId;

    private String secret;

    private String name;

    private String avatar;

    private boolean connected;

    private boolean vip;

    @DBRef
    private Game game;

    private Integer points;

    private Integer roundsCorrects;

//    roundPoints: Number(redisPlayer.roundPoints),
//    isAnswered: String(redisPlayer.isAnswered).toLowerCase() == "true",
//    isCorrectAnswer: String(redisPlayer.isCorrectAnswer).toLowerCase() == "true",
//    roundGuessTime: Number(redisPlayer.roundGuessTime),
//    totalGuessTime: Number(redisPlayer.totalGuessTime),
//    totalCorrectGuesses: Number(redisPlayer.totalCorrectGuesses),
//    finalPosition: Number(redisPlayer.finalPosition)

    public PlayerDTO toDTO() {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setConnected(connected);
        dto.setVip(vip);

        return dto;
    }

    public void incrementPoints(Integer points) {
        this.points += points;
    }

    public void incrementRoundsCorrects() {
        this.roundsCorrects++;
    }

    @JsonIgnore
    public boolean isSecretValid(String other) {
        return Objects.nonNull(secret) && secret.equals(other);
    }
}
