package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.dto.interns.InternTeamDto;
import com.insy2s.gestionbackend.model.InternTeam;

import java.util.List;

public interface InternTeamService {
    InternTeamDto newInternTeam(InternTeamDto internTeamDto);

    List<InternTeamDto> getTeamsByInternId(Long id);

    List<InternTeamDto> getInternsByTeam(Long id);

    InternTeam getInternTeamByInternId(Long internId);

    InternTeam getInternTeamByInternAndTeamId(Long internId, Long teamId);

    void saveInternTeam(InternTeam internTeam);
}
