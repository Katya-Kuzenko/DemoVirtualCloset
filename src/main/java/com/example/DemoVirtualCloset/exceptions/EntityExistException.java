package com.example.DemoVirtualCloset.exceptions;

public class EntityExistException extends RuntimeException {

    public EntityExistException(String message) {
        super(message);
    }
}