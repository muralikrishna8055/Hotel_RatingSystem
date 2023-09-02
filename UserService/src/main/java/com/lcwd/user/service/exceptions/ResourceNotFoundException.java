package com.lcwd.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("resource is not available");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
