package com.example.exam.services.impl;

import com.example.exam.entities.exam.Category;
import com.example.exam.exceptions.ResourceNotFoundException;
import com.example.exam.repositories.CategoryRepository;
import com.example.exam.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        Category category1=categoryRepository.save(category);
        return category1;
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) throws ResourceNotFoundException {
        Category category1=categoryRepository.findById(categoryId).orElseThrow(()->new  ResourceNotFoundException("category is not found with this categoryId"+categoryId));
        category1.setTitle(category.getTitle());
        category1.setDescription(category.getDescription());
        category1.setQuizzes(category.getQuizzes());
        Category category2=categoryRepository.save(category1) ;
        return category2;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories=categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category getCategoryById(Long categoryId) throws ResourceNotFoundException {
        Category category1=categoryRepository.findById(categoryId).orElseThrow(()->new  ResourceNotFoundException("category is not found with this categoryId"+categoryId));

        return category1;
    }

    @Override
    public void deleteCategory(Long categoryId) throws ResourceNotFoundException {
        Category category1=categoryRepository.findById(categoryId).orElseThrow(()->new  ResourceNotFoundException("category is not found with this categoryId"+categoryId));
        categoryRepository.delete(category1);
    }
}
