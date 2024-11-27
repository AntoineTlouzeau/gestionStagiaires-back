package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.dto.interns.InternSkillDto;

import java.util.List;

public interface InternSkillService {

    InternSkillDto newInternSkill(InternSkillDto internSkillDto);
    List<InternSkillDto> getSkillsByInternId(Long id);
    List<InternSkillDto> getInternsBySkill(String skillName);
    InternSkillDto deleteInternSkill(Long id, String skillName);
}
