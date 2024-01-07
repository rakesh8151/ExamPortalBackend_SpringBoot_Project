package com.example.exam.controllers;

import com.example.exam.entities.exam.Question;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.payloads.ApiResponse;
import com.example.exam.services.QuestionService;
import com.example.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question question1=questionService.createQuestion(question);
        return  ResponseEntity.ok(question1);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question,@PathVariable Long questionId) throws ResourceNotFoundException {
        Question question1=questionService.updateQuestion(question,questionId);
        return  ResponseEntity.ok(question1);
    }
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) throws ResourceNotFoundException {
        return  ResponseEntity.ok(questionService.getQuestionById(questionId));
    }
    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestions()  {
        return  ResponseEntity.ok(questionService.getAllQuestions());
    }

    //get all question of quizId;
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsOfQuiz(@PathVariable Long quizId) throws ResourceNotFoundException {
      Quiz quiz= quizService.getQuizById(quizId);
      List<Question> questions= new ArrayList<>(quiz.getQuestions());
      if(questions.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
          questions= questions.subList(0,Integer.parseInt(quiz.getNumberOfQuestions())+1);
      }
      questions.forEach(question -> {
          question.setAnswer("");
      });
        Collections.shuffle(questions);
        return  ResponseEntity.ok(questions);
    }
    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsOfQuizAdmin(@PathVariable Long quizId) throws ResourceNotFoundException {
        Quiz quiz= quizService.getQuizById(quizId);
        List<Question> questions= new ArrayList<>(quiz.getQuestions());

        return  ResponseEntity.ok(questions);
    }
    @DeleteMapping("/{questionId}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Long questionId) throws ResourceNotFoundException {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(new ApiResponse("question is deleted successfully.. ",true, HttpStatus.OK));
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
       double marksGot = 0;
       Integer correctAnswers = 0;
       Integer attempted = 0;
        for (Question q : questions) {
            try {
                Question question = questionService.getQuestionById(q.getQuesId());

                if (q.getGivenAnswer() !=null && !q.getGivenAnswer().trim().equals("")) {
                    attempted += 1;
                    if (q.getGivenAnswer().equals(question.getAnswer().trim())) {
                        correctAnswers += 1;
                    }
                }

            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        marksGot=((Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()))/(questions.size()))*correctAnswers;
      Map<Object,Object> map=  Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
      return  ResponseEntity.ok(map);
    }
}
