package com.insy2s.gestionbackend.errors;

public class InternalServerErrorException extends RuntimeException  {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
