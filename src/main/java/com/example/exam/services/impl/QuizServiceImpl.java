package com.example.exam.services.impl;

import com.example.exam.entities.exam.Category;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.repositories.QuizRepository;
import com.example.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz createQuiz(Quiz quiz) {

            Quiz quiz1 = quizRepository.save(quiz);
            return quiz1 ;

    }

    @Override
    public Quiz updateQuiz(Quiz quiz, Long quizId) throws ResourceNotFoundException {
        Quiz quiz1=quizRepository.findById(quizId).orElseThrow(()-> new ResourceNotFoundException("quiz is not present with this quiz id : "+quizId));
        quiz1.setTitle(quiz.getTitle());
        quiz1.setDescription(quiz.getDescription());
        quiz1.setCategory(quiz.getCategory());
        quiz1.setQuestions(quiz.getQuestions());
        quiz1.setMaxMarks(quiz.getMaxMarks());
        quiz1.setActive(quiz.isActive());
        quiz1.setNumberOfQuestions(quiz.getNumberOfQuestions());
        return quizRepository.save(quiz1);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long quizId) throws ResourceNotFoundException {
        Quiz quiz1=quizRepository.findById(quizId).orElseThrow(()-> new ResourceNotFoundException("quiz is not present with this quiz id : "+quizId));

        return quiz1;
    }

    @Override
    public void deleteQuiz(Long quizId) throws ResourceNotFoundException {
        Quiz quiz1=quizRepository.findById(quizId).orElseThrow(()-> new ResourceNotFoundException("quiz is not present with this quiz id : "+quizId));
       quizRepository.delete(quiz1);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category) {
        return quizRepository.findByCategoryAndActive(category,true);
    }
}
