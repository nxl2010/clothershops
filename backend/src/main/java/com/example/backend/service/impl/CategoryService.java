package com.example.backend.service.impl;

import com.example.backend.dto.request.CategoryDTO;
import com.example.backend.enity.Category;
import com.example.backend.exception.CategoryNotFoundException;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RandomStringService randomStringService;
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setCode(randomStringService.generateRandomString(5));
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() == 0){
            throw new CategoryNotFoundException("Không có giá trị nào cả");
        }
        return categories;
    }

    @Override
    public void delete(Long id) {

    }
}
