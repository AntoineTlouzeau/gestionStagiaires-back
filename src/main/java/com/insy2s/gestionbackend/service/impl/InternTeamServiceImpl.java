package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.dto.interns.InternTeamDto;
import com.insy2s.gestionbackend.dto.interns.mapper.InternTeamMapper;
import com.insy2s.gestionbackend.model.InternTeam;
import com.insy2s.gestionbackend.model.embedded.InternTeamId;
import com.insy2s.gestionbackend.repository.InternRepository;
import com.insy2s.gestionbackend.repository.InternTeamRepository;
import com.insy2s.gestionbackend.repository.TeamRepository;
import com.insy2s.gestionbackend.service.InternTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class InternTeamServiceImpl implements InternTeamService {
    private final InternTeamRepository internTeamRepository;
    private final InternRepository internRepository;
    private final TeamRepository teamRepository;

    @Override
    public InternTeamDto newInternTeam(InternTeamDto internTeamDto){
        InternTeamMapper internTeamMapper = new InternTeamMapper(internRepository, teamRepository);

        InternTeam internTeam = internTeamMapper.convertToEntity(internTeamDto);
        InternTeamId internTeamId = new InternTeamId(internTeam.getIntern().getId(), internTeam.getTeam().getId());

        internTeam.setId(internTeamId);

        return internTeamMapper.convertToDto(internTeamRepository.save(internTeam));
    }

    @Override
    public List<InternTeamDto> getTeamsByInternId(Long id){
        InternTeamMapper internTeamMapper = new InternTeamMapper(internRepository, teamRepository);

        return internTeamRepository.findAll()
                .stream()
                .filter(internTeam -> Objects.equals(internTeam.getIntern().getId(), id))
                .map(internTeamMapper::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<InternTeamDto> getInternsByTeam(Long id){
        InternTeamMapper internTeamMapper = new InternTeamMapper(internRepository, teamRepository);

        return internTeamRepository.findAll()
                .stream()
                .filter(internTeam -> Objects.equals(internTeam.getTeam().getId(), id))
                .map(internTeamMapper::convertToDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public InternTeam getInternTeamByInternId(Long internId) {
        return internTeamRepository.findByIdInternId(internId);
    }

    @Override
    public InternTeam getInternTeamByInternAndTeamId(Long internId, Long teamId) {
        return internTeamRepository.findByIdInternAndIdTeam(internId, teamId).orElse(null);
    }

    @Override
    public void saveInternTeam(InternTeam internTeam) {
        internTeamRepository.save(internTeam);
    }
}