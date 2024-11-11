package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Playlist;
import me.marlon.leoner.musicando.events.domain.game.Question;
import me.marlon.leoner.musicando.events.domain.params.CreateQuestionsRequest;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.repository.QuestionRepository;
import me.marlon.leoner.musicando.events.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    @Value("${msc.manager.url}")
    private String baseUrl;

    private final QuestionRepository repository;

    private final RestService restService;

    private String getKey(String code) {
        return String.format(Constants.QUESTIONS_KEY, code);
    }

    private List<Question> save(List<Question> questions) {
        return repository.saveAll(questions);
    }

    public List<Question> createAndSaveQuestions(RequestStartParams request) {
        CreateQuestionsRequest params = new CreateQuestionsRequest(request);
        List<Question> questions = restService.postList(baseUrl.concat("/questions"), params);
        return save(questions);
    }

    public List<Question> createQuestions(RequestStartParams request) {
        CreateQuestionsRequest params = new CreateQuestionsRequest(request);
        return restService.postList(baseUrl.concat("/questions"), params);
    }

    public Question getQuestionRound(String code, Integer roundNumber) {
        String key = getKey(code);
//        return redisService.getItemInList(key, roundNumber - 1, Question.class);
        return null;
    }

    public void removeQuestions(Game game) {
        String key = getKey(game.getCode());
//        redisService.remove(key);
    }
}
