package com.example.backend.exception;

public class SupplierNotFoundException extends RuntimeException{
    public SupplierNotFoundException(String code){
        super("NOTFOUND" +code);
    }
}
