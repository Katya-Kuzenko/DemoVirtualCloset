package com.example.DemoVirtualCloset.exceptions;

public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }
}