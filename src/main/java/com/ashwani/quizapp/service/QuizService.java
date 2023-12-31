package com.ashwani.quizapp.service;

import com.ashwani.quizapp.dao.QuestionDao;
import com.ashwani.quizapp.dao.QuizDao;
import com.ashwani.quizapp.model.Question;
import com.ashwani.quizapp.model.QuestionWrapper;
import com.ashwani.quizapp.model.Quiz;
import com.ashwani.quizapp.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizdao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int noQ, String title) {
        List<Question> questions = questionDao.findNoOfQuestionsByCategoey(noQ, category);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizdao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(), q.getOption2(), q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<UserResponse> userResponses) {
        Quiz quiz = quizdao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int result = 0;
        int i = 0;
        for(UserResponse userResponse : userResponses){
            if(userResponse.getUserResponse().equals(questions.get(i).getRightAnswer()))
                result++;
            i++;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
