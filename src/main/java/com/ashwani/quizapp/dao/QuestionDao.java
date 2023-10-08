package com.ashwani.quizapp.dao;

import com.ashwani.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category); // Spring samajdar hai, jo function name mey Category hai whi same (category) naam se ek column hai DB mey.

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY Random() LIMIT :noQ", nativeQuery = true)
    List<Question> findNoOfQuestionsByCategoey(int noQ, String category);
}
