package com.insy2s.gestionbackend.service.impl;


import com.insy2s.gestionbackend.dto.teams.TeamDto;
import com.insy2s.gestionbackend.dto.teams.TeamLiteDto;
import com.insy2s.gestionbackend.dto.teams.TeamWithSkillsAndManagersDto;
import com.insy2s.gestionbackend.dto.teams.mapper.TeamMapper;
import com.insy2s.gestionbackend.errors.InternalServerErrorException;
import com.insy2s.gestionbackend.errors.ResourceNotFoundException;
import com.insy2s.gestionbackend.errors.intern.InternAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.intern.InternNotAssociatedException;
import com.insy2s.gestionbackend.errors.intern.InternNotFoundException;
import com.insy2s.gestionbackend.errors.manager.ManagerAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotAssociatedException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotFoundException;
import com.insy2s.gestionbackend.errors.skill.SkillNotAssociatedException;
import com.insy2s.gestionbackend.errors.skill.SkillNotFoundException;
import com.insy2s.gestionbackend.errors.team.TeamAlreadyUpdatedException;
import com.insy2s.gestionbackend.errors.team.TeamNotFoundException;
import com.insy2s.gestionbackend.errors.team.TeamUpdateFailedException;
import com.insy2s.gestionbackend.filter.TeamSpecifications;
import com.insy2s.gestionbackend.model.*;
import com.insy2s.gestionbackend.model.embedded.InternTeamId;
import com.insy2s.gestionbackend.repository.*;
import com.insy2s.gestionbackend.service.TeamService;
import com.insy2s.gestionbackend.utils.DateConversionUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;
    private final ManagerRepository managerRepository;
    private final InternRepository internRepository;
    private final InternTeamRepository internTeamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
    }

    @Override
    public Page<TeamWithSkillsAndManagersDto> getAllTeamsWithMemberCountAndFilter(
            Boolean isWeekEven,
            String name,
            String skillId,
            String projectState,
            String lastName,
            Pageable pageable) {
        TeamSpecifications teamSpecifications = new TeamSpecifications(skillRepository, lastName);
        try {
            Specification<Team> specification = Specification.where(null);
            if (isWeekEven != null) { specification = specification.and(teamSpecifications.hasIsWeekEven(isWeekEven)); }
            if (name != null && !name.isEmpty()) {specification = specification.and(teamSpecifications.hasNameContaining(name));}
            if (skillId != null && !skillId.isEmpty()) {specification = specification.and(teamSpecifications.hasSkillId(skillId));}
            if (lastName != null && !lastName.isEmpty()) {specification = specification.and(teamSpecifications.hasManagerLastNameContaining(lastName));}
            if (projectState != null && !projectState.isEmpty()) {
                if (projectState.equalsIgnoreCase("finished")) {
                    specification = specification.and((root, query, criteriaBuilder) -> {
                        Date currentDate = new Date();
                        return criteriaBuilder.lessThan(root.get("projectEndDate"), currentDate);
                    });
                } else if (projectState.equalsIgnoreCase("unfinished")) {
                    specification = specification.and((root, query, criteriaBuilder) -> {
                        Date currentDate = new Date();
                        return criteriaBuilder.greaterThanOrEqualTo(root.get("projectEndDate"), currentDate);
                    });
                }
            }

            Page<Team> teamPage = teamRepository.findAll(specification, pageable);

            List<TeamWithSkillsAndManagersDto> teamWithSkillsAndManagersDtos = new ArrayList<>();
            for (Team team : teamPage.getContent()) { //TODO check if foreach is better
                TeamWithSkillsAndManagersDto teamWithSkillsAndManagersDto = teamMapper.convertToDto(team);
                int memberCount = teamRepository.findMemberCountByTeamId(team.getId());
                teamWithSkillsAndManagersDto.setMemberCount(memberCount);
                teamWithSkillsAndManagersDtos.add(teamWithSkillsAndManagersDto);
            }

            if (teamWithSkillsAndManagersDtos.isEmpty()) {
                throw new ResourceNotFoundException("Aucun résultat avec ces filtres !");
            }

            return new PageImpl<>(teamWithSkillsAndManagersDtos, pageable, teamPage.getTotalElements());
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Oops, erreur interne du serveur. Try again !");
        }
    }

    @Override
    @Transactional
    public Team addSkillToTeam(Long teamId, String skillName) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found"));
        Skill skill = skillRepository.findById(skillName)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found"));
        team.getSkills().add(skill);
        skill.getTeams().add(team);
        return teamRepository.save(team);
    }

    @Override
    @Transactional
    public void removeSkillFromTeam(Long teamId, String skillName) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
        Skill skill = skillRepository.findById(skillName)
                .orElseThrow(() -> new SkillNotFoundException("Skill not found with name: " + skillName));
        if (!team.getSkills().contains(skill)) {
            throw new SkillNotAssociatedException("The skill: " + skillName + " is not associated with the team having id: " + teamId);
        }
        team.getSkills().remove(skill);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void addManagerToTeam(Long teamId, Long managerId)
            throws ManagerAlreadyInTeamException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ManagerNotFoundException("Manager not found with id: " + managerId));
        if (team.getManagers().contains(manager)) {
            throw new ManagerAlreadyInTeamException(
                    "Manager " + manager.getLastName() + " " + manager.getFirstName() + " (ID: " + managerId +
                            ") is already in team " + team.getName() + " (ID: " + teamId + ")"
            );
        }
        team.getManagers().add(manager);
        manager.getTeams().add(team);
        teamRepository.save(team);
        managerRepository.save(manager);
    }

    @Override
    @Transactional
    public Intern addInternToTeam(Long teamId, Long internId, LocalDate startDate, LocalDate endDate)
            throws InternAlreadyInTeamException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
        Intern intern = internRepository.findById(internId)
                .orElseThrow(() -> new InternNotFoundException("Intern not found with id: " + internId));
        if (internTeamRepository.existsByInternIdAndTeamId(internId, teamId)) {
            throw new InternAlreadyInTeamException(
                    "Intern " + intern.getLastName() + " " + intern.getFirstName() + " (ID: " + internId + ")" +
                            " is already in team " + team.getName() + " (ID: " + teamId + ")"
            );
        }
        InternTeamId internTeamId = new InternTeamId(internId, teamId);
        InternTeam internTeam = new InternTeam();
        internTeam.setId(internTeamId);
        internTeam.setStartDate(DateConversionUtils.toDateFromLocalDate(startDate));
        internTeam.setEndDate(DateConversionUtils.toDateFromLocalDate(endDate));
        internTeamRepository.save(internTeam);
        return intern;
    }

    @Override
    @Transactional
    public void removeInternFromTeam(Long teamId, Long internId) throws InternNotAssociatedException {
        InternTeamId internTeamId = new InternTeamId(internId, teamId);
        if (internTeamRepository.existsById(internTeamId)) {
            internTeamRepository.deleteById(internTeamId);
        } else {
            throw new InternNotAssociatedException("Intern with id: " + internId + " is not associated with the team: " + teamId);
        }
    }

    @Override
    @Transactional
    public void removeManagerFromTeam(Long teamId, Long managerId) throws ManagerNotAssociatedException {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new ManagerNotFoundException("Manager not found with id: " + managerId));
        if (!team.getManagers().contains(manager)) {
            throw new ManagerNotAssociatedException("The manager: " + managerId + " is not associated with the team having id: " + teamId);
        }
        team.getManagers().remove(manager);
        manager.getTeams().remove(team);
        teamRepository.save(team);
        managerRepository.save(manager);
    }

    @Override
    public List<Intern> getInternsByTeamId(Long teamId) {
        return internTeamRepository.findInternsByTeamId(teamId);
    }

    @Override
    @Transactional
    public Team modifyTeam(Long teamId, TeamLiteDto teamLiteDto) throws TeamAlreadyUpdatedException, TeamUpdateFailedException {
        Team teamFromDb = teamRepository.findById(teamId)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + teamId));
        teamFromDb.setName(teamLiteDto.getName());
        teamFromDb.setProjectStartDate(teamLiteDto.getStartDate());
        teamFromDb.setProjectEndDate(teamLiteDto.getEndDate());
        teamFromDb.setIsWeekEven(teamLiteDto.getIsWeekEven());
        teamFromDb.setUrlRepository(teamLiteDto.getUrlRepository());
        teamFromDb.setUrlBacklog(teamLiteDto.getUrlBacklog());
        try {
            return teamRepository.save(teamFromDb);
        } catch (DataIntegrityViolationException e) {
            throw new TeamUpdateFailedException
                    ("There was an integrity violation while updating the team with name: " + teamLiteDto.getName());
        } catch (OptimisticLockingFailureException e) {
            throw new TeamAlreadyUpdatedException
                    ("The team data was updated by another user. Please refresh and try again.");
        }
    }

    // NEW :
    @Override
    public TeamDto newTeam(TeamDto teamDto){

        Team team = TeamMapper.TeamDtoToTeam(teamDto);
        if(team != null){teamRepository.save(team);}
        return teamDto;
    }

}
