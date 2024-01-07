package com.example.exam.services.impl;

import com.example.exam.entities.exam.Question;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.repositories.QuestionRepository;
import com.example.exam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question, Long questionId) throws ResourceNotFoundException {
        Question question1=questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question is not present with this questionId : "+questionId));

        question1.setContent(question.getContent());
        question1.setImage(question.getImage());
        question1.setQuiz(question.getQuiz());
        question1.setOption1(question.getOption1());
        question1.setOption2(question.getOption2());
        question1.setOption3(question.getOption3());
        question1.setOption4(question.getOption4());
        question1.setAnswer(question.getAnswer());
        return questionRepository.save(question1);
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> questions=questionRepository.findAll();
        return questions;
    }

    @Override
    public Question getQuestionById(Long questionId) throws ResourceNotFoundException {

          Question question1=questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question is not present with this questionId : "));

        return question1;
    }

    @Override
    public List<Question> getQuestionsOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionId) throws ResourceNotFoundException {
        Question question1=questionRepository.findById(questionId).orElseThrow(()-> new ResourceNotFoundException("question is not present with this questionId : "+questionId));
       questionRepository.delete(question1);
    }
}
