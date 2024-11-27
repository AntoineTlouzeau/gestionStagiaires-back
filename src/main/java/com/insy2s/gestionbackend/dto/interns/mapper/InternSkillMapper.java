package com.insy2s.gestionbackend.dto.interns.mapper;

import com.insy2s.gestionbackend.dto.interns.InternSkillDto;
import com.insy2s.gestionbackend.model.InternSkill;
import com.insy2s.gestionbackend.repository.InternRepository;
import com.insy2s.gestionbackend.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InternSkillMapper {
    private final InternRepository internRepository;
    private final SkillRepository skillRepository;

    public InternSkillDto convertToDto(InternSkill internSkill){
        return InternSkillDto.builder()
                .internId(internSkill.getIntern().getId())
                .skill(internSkill.getSkill().getSkillName())
                .level(internSkill.getLevel())
                .build();
    }

    public InternSkill convertToEntity(InternSkillDto internSkillDto){
        return InternSkill.builder()
                .intern(internRepository.findById(internSkillDto.getInternId()).orElse(null))
                .skill(skillRepository.findById(internSkillDto.getSkill()).orElse(null))
                .level(internSkillDto.getLevel())
                .build();
    }
}
