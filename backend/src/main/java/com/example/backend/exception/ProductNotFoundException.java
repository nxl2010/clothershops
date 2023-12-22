package com.example.backend.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String role) {
        super("Product not found: " + role);
    }
}
