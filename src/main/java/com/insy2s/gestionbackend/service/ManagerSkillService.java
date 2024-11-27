package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.dto.managers.ManagerSkillDto;

import java.util.List;

public interface ManagerSkillService {
    ManagerSkillDto newManagerSkill(ManagerSkillDto managerSkillDto);
    List<ManagerSkillDto> getSkillsByManagerId(Long id);
    List<ManagerSkillDto> getManagersBySkill(String skillName);
    ManagerSkillDto deleteManagerSkill(Long id, String skillName);
}