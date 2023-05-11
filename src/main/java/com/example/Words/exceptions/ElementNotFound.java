package com.example.Words.exceptions;

public class ElementNotFound extends RuntimeException{

    public ElementNotFound(String message) {
        super(message);
    }
}
