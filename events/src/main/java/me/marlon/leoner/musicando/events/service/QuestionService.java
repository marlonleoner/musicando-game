package me.marlon.leoner.musicando.events.service;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.domain.game.Game;
import me.marlon.leoner.musicando.events.domain.game.Question;
import me.marlon.leoner.musicando.events.domain.params.CreateQuestionsRequest;
import me.marlon.leoner.musicando.events.domain.params.RequestStartParams;
import me.marlon.leoner.musicando.events.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class QuestionService {

    @Value("${msc.manager.url}")
    private String baseUrl;

    private final QuestionRepository repository;

    private final RestService restService;

    private List<Question> save(List<Question> questions) {
        return repository.saveAll(questions);
    }

    public void remove(Game game) {
        repository.deleteAllByGame(game);
    }

    public void createAndSaveQuestions(Game game, RequestStartParams request) {
        remove(game);

        CreateQuestionsRequest params = new CreateQuestionsRequest(request);
        List<Question> questions = restService.postList(baseUrl.concat("/questions"), params, Question.class);
        IntStream.range(0, questions.size()).forEach(index -> {
            Question question = questions.get(index);
            question.setGame(game);
            question.setRoundNumber(index + 1);
        });

        save(questions);
    }

    public Question getNextRoundInGame(Game game) {
        return repository.findQuestionByGameAndRoundNumber(game, game.getNextRoundNumber()).orElse(null);
    }
}
