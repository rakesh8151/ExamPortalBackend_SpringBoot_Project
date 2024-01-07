package com.example.exam.services;

import com.example.exam.entities.exam.Category;
import com.example.exam.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
     Category createCategory(Category category);
    Category updateCategory(Category category,Long categoryId) throws ResourceNotFoundException;
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId) throws ResourceNotFoundException;
    void deleteCategory(Long categoryId) throws ResourceNotFoundException;


}
