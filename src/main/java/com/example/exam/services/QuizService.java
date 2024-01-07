package com.example.exam.services;

import com.example.exam.entities.exam.Category;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;

import java.util.List;

public interface QuizService {

    Quiz createQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz ,Long quizId) throws ResourceNotFoundException;
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long quizId) throws ResourceNotFoundException;
    void deleteQuiz(Long quizId) throws ResourceNotFoundException;

    List<Quiz> getQuizzesOfCategory(Category category);

    List<Quiz> getActiveQuizzes();
    List<Quiz> getActiveQuizzesOfCategory(Category category);
}
