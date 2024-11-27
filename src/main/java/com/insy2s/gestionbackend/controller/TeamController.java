package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.dto.interns.AddInternDto;
import com.insy2s.gestionbackend.dto.interns.InternLiteDto;
import com.insy2s.gestionbackend.dto.interns.mapper.InternLiteMapper;
import com.insy2s.gestionbackend.dto.teams.TeamDto;
import com.insy2s.gestionbackend.dto.teams.TeamLiteDto;
import com.insy2s.gestionbackend.dto.teams.TeamWithSkillsAndManagersDto;
import com.insy2s.gestionbackend.dto.teams.mapper.TeamLiteMapper;
import com.insy2s.gestionbackend.dto.teams.mapper.TeamMapper;
import com.insy2s.gestionbackend.errors.intern.InternAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.intern.InternNotAssociatedException;
import com.insy2s.gestionbackend.errors.manager.ManagerAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotAssociatedException;
import com.insy2s.gestionbackend.errors.team.TeamAlreadyUpdatedException;
import com.insy2s.gestionbackend.errors.team.TeamUpdateFailedException;
import com.insy2s.gestionbackend.model.Intern;
import com.insy2s.gestionbackend.model.Team;
import com.insy2s.gestionbackend.repository.InternTeamRepository;
import com.insy2s.gestionbackend.service.TeamService;
import com.insy2s.gestionbackend.utils.DateConversionUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;
    private final InternLiteMapper internLiteMapper;
    private final InternTeamRepository internTeamRepository;
    private final TeamLiteMapper teamLiteMapper;

    @GetMapping
    public ResponseEntity<Page<TeamWithSkillsAndManagersDto>> getAllTeamsWithPaginationAndFilterWithMemberCount(
            @RequestParam(required = false) Boolean isWeekEven,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String skillId,
            @RequestParam(required = false) String projectState,
            @RequestParam(required = false) String lastName,
            Pageable pageable) {
        Page<TeamWithSkillsAndManagersDto> teamPage = teamService.getAllTeamsWithMemberCountAndFilter(isWeekEven, name, skillId, projectState, lastName, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(teamPage);
    }

    @GetMapping("/{teamId}")
    public TeamWithSkillsAndManagersDto getTeamById(@PathVariable Long teamId) {
        Team team = teamService.getTeamById(teamId);
        return teamMapper.convertToDto(team);
    }

    @PutMapping("/{teamId}/skills/{skillName}")
    public TeamWithSkillsAndManagersDto addSkillToTeam(@PathVariable Long teamId, @PathVariable String skillName) {
        Team team = teamService.addSkillToTeam(teamId, skillName);
        return teamMapper.convertToDto(team);
    }

    @DeleteMapping("/{teamId}/skills/{skillName}")
    public ResponseEntity<Void> removeSkillFromTeam(@PathVariable Long teamId, @PathVariable String skillName) {
        teamService.removeSkillFromTeam(teamId, skillName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{teamId}/addManager/{managerId}")
    public ResponseEntity<Void> addManagerToTeam(
            @PathVariable Long teamId,
            @PathVariable Long managerId) throws ManagerAlreadyInTeamException {
        teamService.addManagerToTeam(teamId, managerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{teamId}/addIntern/{internId}")
    public ResponseEntity<InternLiteDto> addInternToTeam(
            @PathVariable Long teamId,
            @PathVariable Long internId,
            @Valid @RequestBody AddInternDto addInternDto) throws InternAlreadyInTeamException {

        Intern addedIntern = teamService.addInternToTeam(teamId, internId, addInternDto.getStartDate(), addInternDto.getEndDate());
        InternLiteDto internLiteDto = internLiteMapper.convertToDto(addedIntern);
        internLiteDto.setStartDate(DateConversionUtils.toDateFromLocalDate(addInternDto.getStartDate()));
        internLiteDto.setEndDate(DateConversionUtils.toDateFromLocalDate(addInternDto.getEndDate()));
        return ResponseEntity.ok(internLiteDto);
    }

    @DeleteMapping("/{teamId}/removeIntern/{internId}")
    public ResponseEntity<String> removeInternFromTeam(
            @PathVariable
            Long teamId,
            @PathVariable Long internId
    ) throws InternNotAssociatedException {

        teamService.removeInternFromTeam(teamId, internId);
        return ResponseEntity.ok("Intern removed from the team successfully!");
    }

    @DeleteMapping("/{teamId}/removeManager/{managerId}")
    public ResponseEntity<String> removeManagerFromTeam(
            @PathVariable Long teamId,
            @PathVariable Long managerId
    ) throws ManagerNotAssociatedException {

        teamService.removeManagerFromTeam(teamId, managerId);
        return ResponseEntity.ok("Manager removed from the team successfully!");
    }

    @GetMapping("/interns/{teamId}")
    public ResponseEntity<List<InternLiteDto>> getInternsByTeamId(
            @PathVariable Long teamId
    ) {

        List<Intern> interns = internTeamRepository.findInternsByTeamId(teamId);
        List<InternLiteDto> dtos = interns.stream()
                .map(internLiteMapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PutMapping("/modifyTeam/{teamId}")
    public ResponseEntity<TeamLiteDto> modifyTeam(
            @PathVariable Long teamId,
            @Valid @RequestBody TeamLiteDto teamLiteDto
    ) throws TeamAlreadyUpdatedException, TeamUpdateFailedException {

        Team team = teamService.modifyTeam(teamId, teamLiteDto);
        return ResponseEntity.ok(teamLiteMapper.convertToDto(team));
    }
    //NEW:
    @PostMapping("/new")
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto teamDto){
        return ResponseEntity.status(HttpStatus.OK).body(teamService.newTeam(teamDto));
    }



}

