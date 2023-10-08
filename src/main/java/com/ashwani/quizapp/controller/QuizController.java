package com.ashwani.quizapp.controller;

import com.ashwani.quizapp.model.QuestionWrapper;
import com.ashwani.quizapp.model.UserResponse;
import com.ashwani.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noQ, @RequestParam String title){
        return quizService.createQuiz(category, noQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<UserResponse> userResponses){
        return quizService.calculateScore(id, userResponses);
    }
}
