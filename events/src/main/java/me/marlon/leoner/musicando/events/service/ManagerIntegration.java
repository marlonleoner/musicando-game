package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Playlist;
import me.marlon.leoner.musicando.events.domain.game.Question;
import me.marlon.leoner.musicando.events.domain.params.CreateQuestionsRequest;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerIntegration {

    @Value("${msc.manager.url}")
    private String baseUrl;

    private final RestService restService;

    public Optional<Playlist> getPlaylistById(String playlistId) {
        Playlist playlist = restService.get(baseUrl.concat("/playlists/").concat(playlistId), Playlist.class);
        return Optional.ofNullable(playlist);
    }

    public List<Question> generateQuestions(RequestStartParams request) {
        CreateQuestionsRequest params = new CreateQuestionsRequest(request);
        return restService.postList(baseUrl.concat("/questions"), params, Question.class);
    }
}
