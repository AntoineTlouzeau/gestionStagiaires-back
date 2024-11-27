package com.insy2s.gestionbackend.dto.interns.mapper;

import com.insy2s.gestionbackend.dto.interns.InternTeamDto;
import com.insy2s.gestionbackend.model.InternTeam;
import com.insy2s.gestionbackend.repository.InternRepository;
import com.insy2s.gestionbackend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InternTeamMapper {
    private final InternRepository internRepository;
    private final TeamRepository teamRepository;

    public InternTeamDto convertToDto(InternTeam internTeam){
        return InternTeamDto.builder()
                .internId(internTeam.getIntern().getId())
                .teamId(internTeam.getTeam().getId())
                .startDate(internTeam.getStartDate())
                .endDate(internTeam.getEndDate())
                .build();
    }

    public InternTeam convertToEntity(InternTeamDto internTeamDto){
        return InternTeam.builder()
                .intern(internRepository.findById(internTeamDto.getInternId()).orElse(null))
                .team(teamRepository.findById(internTeamDto.getTeamId()).orElse(null))
                .startDate(internTeamDto.getStartDate())
                .endDate(internTeamDto.getEndDate())
                .build();
    }
}
