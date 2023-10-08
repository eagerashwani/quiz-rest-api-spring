package com.ashwani.quizapp.dao;

import com.ashwani.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category); // Spring samajdar hai, jo function name mey Category hai whi same (category) naam se ek column hai DB mey.
}
