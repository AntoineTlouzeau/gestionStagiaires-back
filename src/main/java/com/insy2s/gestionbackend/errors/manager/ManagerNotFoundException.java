package com.insy2s.gestionbackend.errors.manager;

public class ManagerNotFoundException extends RuntimeException{

    public ManagerNotFoundException(String message) {
        super(message);
    }
}
