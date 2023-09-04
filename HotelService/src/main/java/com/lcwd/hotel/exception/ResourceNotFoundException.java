package com.lcwd.hotel.exception;

import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Requested resource is not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
