package com.insy2s.gestionbackend.errors.skill;

public class SkillNotFoundException  extends RuntimeException{
    public SkillNotFoundException(String message) {
        super(message);
    }
}
