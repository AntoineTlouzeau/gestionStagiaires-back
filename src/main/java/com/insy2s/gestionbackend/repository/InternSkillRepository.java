package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.InternSkill;
import com.insy2s.gestionbackend.model.embedded.InternSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternSkillRepository extends JpaRepository<InternSkill, InternSkillId> {
}
