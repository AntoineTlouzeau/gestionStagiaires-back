package com.insy2s.gestionbackend.errors.team;

public class TeamAlreadyExistsException extends RuntimeException{
    public TeamAlreadyExistsException(String message) {
        super(message);
    }
}
