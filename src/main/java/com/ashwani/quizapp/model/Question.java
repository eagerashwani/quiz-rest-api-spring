package com.ashwani.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // hmme har field k liye getter and setters bnane pdte, ye kaam lombok kr rha hai
@Entity
public class Question {
//    hmare db mey jo columns hai usk corresponding ye Question model hai, ye krne k liye @Entity use krte hai
//    SQL mey snake casing hoti hai, whin java mey camel casing, to hmmne same naam dene hone taki db k column se map ho ske
//    JPA khud snake case kr dega camel case ko

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;
}
