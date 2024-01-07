package com.example.exam.services;



import com.example.exam.entities.exam.Question;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Question question,Long questionId) throws ResourceNotFoundException;
    List< Question > getAllQuestions();
    Question  getQuestionById(Long questionId) throws ResourceNotFoundException;

    List<Question> getQuestionsOfQuiz(Quiz quiz);
    void deleteQuestion(Long questionId) throws ResourceNotFoundException;
}
