package com.example.exam.controllers;

import com.example.exam.entities.exam.Category;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.payloads.ApiResponse;
import com.example.exam.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category category1=categoryService.createCategory(category);
        return  ResponseEntity.ok(category1);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category,@PathVariable Long categoryId) throws ResourceNotFoundException {
        Category category1=categoryService.updateCategory(category,categoryId);
        return  ResponseEntity.ok(category1);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) throws ResourceNotFoundException {
        Category category1=categoryService.getCategoryById(categoryId);
        return  ResponseEntity.ok(category1);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
       List<Category> category1=categoryService.getAllCategories();
        return  ResponseEntity.ok(category1);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) throws ResourceNotFoundException {
       categoryService.deleteCategory(categoryId);
        return  ResponseEntity.ok(new ApiResponse("category is deleted successfully.. ",true, HttpStatus.OK));
    }

}
