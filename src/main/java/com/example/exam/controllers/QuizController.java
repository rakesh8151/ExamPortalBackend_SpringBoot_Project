package com.example.exam.controllers;

import com.example.exam.entities.exam.Category;
import com.example.exam.entities.exam.Quiz;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.payloads.ApiResponse;
import com.example.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1= quizService.createQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz,@PathVariable Long quizId) throws ResourceNotFoundException {
        Quiz quiz1=quizService.updateQuiz(quiz,quizId);
        return ResponseEntity.ok(quiz1);
    }
    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) throws ResourceNotFoundException {
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getQuizzes()  {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable Long quizId) throws ResourceNotFoundException {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok(new ApiResponse("quiz is deleted successfully.. ",true, HttpStatus.OK));
    }

    @GetMapping("/category/{cid}")
    public  ResponseEntity<List<Quiz>> getQuizzesOfCategory(@PathVariable Long cid){
        Category category=new Category();
        category.setCid(cid);
        return  ResponseEntity.ok(quizService.getQuizzesOfCategory(category));

    }

    // get active quizzes
    @GetMapping("/active")
    public  ResponseEntity<List<Quiz>> getActiveQuizzes(){
        return  ResponseEntity.ok(quizService.getActiveQuizzes());
    }

    // get active quizzes of category
    @GetMapping("/category/active/{categoryId}")
    public  ResponseEntity<List<Quiz>> getActiveQuizzesOfCategory(@PathVariable Long categoryId){
        Category category=new Category();
        category.setCid(categoryId);
        return  ResponseEntity.ok(quizService.getActiveQuizzesOfCategory(category));
    }

}
