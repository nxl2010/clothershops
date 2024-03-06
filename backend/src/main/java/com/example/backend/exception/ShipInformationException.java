package com.example.backend.exception;

public class ShipInformationException extends RuntimeException{
    public ShipInformationException(String category){
        super(category);
    }
}
