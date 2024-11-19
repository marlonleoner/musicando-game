package me.marlon.leoner.musicando.manager.aggregation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.marlon.leoner.musicando.manager.domain.Playlist;
import me.marlon.leoner.musicando.manager.domain.Song;
import me.marlon.leoner.musicando.manager.domain.dto.QuestionDTO;
import me.marlon.leoner.musicando.manager.domain.params.GenerateQuestionsParams;
import me.marlon.leoner.musicando.manager.exception.ObjectNotFoundException;
import me.marlon.leoner.musicando.manager.service.PlaylistService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionAggregation {

    private final PlaylistService playlistService;

    public List<QuestionDTO> generateQuestions(GenerateQuestionsParams params) {
        Playlist playlist = playlistService.findPlaylistById(params.getPlaylistId());
        if (Objects.isNull(playlist)) {
            throw new ObjectNotFoundException("playlist not found");
        }

        List<Song> songs = new ArrayList<>(playlist.getSongs());
        Collections.shuffle(songs);
        return IntStream.range(0, params.getAmount()).mapToObj(i -> {
            Song answer = songs.remove(i);

            List<Song> clone = new ArrayList<>(songs);
            List<Song> alternatives = new ArrayList<>(IntStream.range(0, 3).mapToObj(a -> clone.remove((int) Math.floor(Math.random() * clone.size()))).toList());
            alternatives.add(answer);

            Collections.shuffle(alternatives);

            return new QuestionDTO(answer.toDTO(), alternatives.stream().map(Song::toDTO).toList());
        }).toList();
    }
}
