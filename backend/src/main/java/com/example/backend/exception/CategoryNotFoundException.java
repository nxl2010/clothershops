package com.example.backend.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String category){
        super(category);
    }
}
