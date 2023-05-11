package com.example.Words.exceptions;

public class SuchElementAlreadyExist extends RuntimeException{


    public SuchElementAlreadyExist(String message) {
        super("Such element already exist");

    }
}
