package com.example.backend.api;

import com.example.backend.dto.request.CategoryDTO;
import com.example.backend.enity.Category;
import com.example.backend.service.impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<Category> categories = categoryService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }
    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO name){
        categoryService.save(name);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> findByCode(@PathVariable    String code){
        try{
            String name = categoryService.findByCode(code);
            return new ResponseEntity<>(name, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }
}
