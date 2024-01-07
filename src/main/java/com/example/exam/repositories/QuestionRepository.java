package com.example.exam.repositories;

import com.example.exam.entities.exam.Question;
import com.example.exam.entities.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {


    List<Question> findByQuiz(Quiz quiz);
}
