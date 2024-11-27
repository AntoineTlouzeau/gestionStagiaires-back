package com.insy2s.gestionbackend.dto.skills.mapper;

import com.insy2s.gestionbackend.dto.skills.SkillDto;
import com.insy2s.gestionbackend.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SkillMapper {
    public SkillDto convertToDto(Skill skill) {
        return SkillDto.builder()
                .skillName(skill.getSkillName())
                .build();
    }
    public Skill convertToEntity(SkillDto skillDto) {
        return Skill.builder()
                .skillName(skillDto.getSkillName())
                .build();
    }
}