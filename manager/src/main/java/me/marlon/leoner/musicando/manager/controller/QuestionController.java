package me.marlon.leoner.musicando.manager.controller;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.manager.aggregation.QuestionAggregation;
import me.marlon.leoner.musicando.manager.domain.dto.QuestionDTO;
import me.marlon.leoner.musicando.manager.domain.params.GenerateQuestionsParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionAggregation aggregation;

    @PostMapping
    public ResponseEntity<List<QuestionDTO>> generate(@RequestBody GenerateQuestionsParams params) {
        return ResponseEntity.ok(aggregation.generateQuestions(params));
    }
}
