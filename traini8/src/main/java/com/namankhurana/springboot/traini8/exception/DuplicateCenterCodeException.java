package com.namankhurana.springboot.traini8.exception;

public class DuplicateCenterCodeException extends RuntimeException{
    public DuplicateCenterCodeException(String message) {
        super(message);
    }
}
