package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.model.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> getAllSkills();
    Skill createSkill(Skill skill);
    Skill newSkill(Skill skill);
    Skill getSkillByName(String name);
}