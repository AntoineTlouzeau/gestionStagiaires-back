package com.insy2s.gestionbackend.service;


import com.insy2s.gestionbackend.dto.teams.TeamDto;
import com.insy2s.gestionbackend.dto.teams.TeamLiteDto;
import com.insy2s.gestionbackend.dto.teams.TeamWithSkillsAndManagersDto;
import com.insy2s.gestionbackend.errors.intern.InternAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.intern.InternNotAssociatedException;
import com.insy2s.gestionbackend.errors.manager.ManagerAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotAssociatedException;
import com.insy2s.gestionbackend.errors.team.TeamAlreadyUpdatedException;
import com.insy2s.gestionbackend.errors.team.TeamUpdateFailedException;
import com.insy2s.gestionbackend.model.Intern;
import com.insy2s.gestionbackend.model.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface TeamService {

    Team getTeamById(Long teamId);

    Page<TeamWithSkillsAndManagersDto> getAllTeamsWithMemberCountAndFilter(
            Boolean isWeekEven,
            String name,
            String skillId,
            String projectState,
            String lastName,
            Pageable pageable);

    @Transactional
    Team addSkillToTeam(Long teamId, String skillName);
    @Transactional
    void removeSkillFromTeam(Long teamId, String skillName);
    @Transactional
    void addManagerToTeam(Long teamId, Long managerId)
            throws ManagerAlreadyInTeamException;
    @Transactional
    Intern addInternToTeam(Long teamId, Long internId, LocalDate startDate, LocalDate endDate)
            throws InternAlreadyInTeamException;
    @Transactional
    void removeInternFromTeam(Long teamId, Long internId) throws InternNotAssociatedException;
    @Transactional
    void removeManagerFromTeam(Long teamId, Long managerId) throws ManagerNotAssociatedException;
    List<Intern> getInternsByTeamId(Long teamId);
    @Transactional
    Team modifyTeam(Long teamId, TeamLiteDto teamLiteDto) throws TeamAlreadyUpdatedException, TeamUpdateFailedException;


    // NEW :
    TeamDto newTeam(TeamDto teamDto);

}
