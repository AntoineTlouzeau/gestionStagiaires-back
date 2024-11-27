package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.ManagerSkill;
import com.insy2s.gestionbackend.model.embedded.ManagerSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerSkillRepository extends JpaRepository<ManagerSkill, ManagerSkillId> {
}