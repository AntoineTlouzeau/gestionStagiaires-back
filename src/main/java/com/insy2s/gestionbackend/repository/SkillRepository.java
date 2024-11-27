package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, String> {
}