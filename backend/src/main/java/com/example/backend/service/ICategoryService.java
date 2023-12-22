package com.example.backend.service;

import com.example.backend.dto.request.CategoryDTO;
import com.example.backend.enity.Category;

import java.util.List;

public interface ICategoryService {
    void save(CategoryDTO name);
    void update(Category category);
    List<Category> findAll();
    void delete(Long id);
}
