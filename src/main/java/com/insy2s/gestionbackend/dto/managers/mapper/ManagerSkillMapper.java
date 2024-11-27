package com.insy2s.gestionbackend.dto.managers.mapper;

import com.insy2s.gestionbackend.dto.managers.ManagerSkillDto;
import com.insy2s.gestionbackend.model.ManagerSkill;
import com.insy2s.gestionbackend.repository.ManagerRepository;
import com.insy2s.gestionbackend.repository.SkillRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ManagerSkillMapper {
    private final ManagerRepository managerRepository;
    private final SkillRepository skillRepository;

    public ManagerSkillDto managerSkillToManagerSkillDto(ManagerSkill managerSkill){
        return ManagerSkillDto.builder()
                .managerId(managerSkill.getManager().getId())
                .skill(managerSkill.getSkill().getSkillName())
                .level(managerSkill.getLevel())
                .build();
    }

    public ManagerSkill managerSkillDtoToManagerSkill(ManagerSkillDto managerSkillDto){
        return ManagerSkill.builder()
                .manager(managerRepository.findById(managerSkillDto.getManagerId()).orElse(null))
                .skill(skillRepository.findById(managerSkillDto.getSkill()).orElse(null))
                .level(managerSkillDto.getLevel())
                .build();
    }
}