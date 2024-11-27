package com.insy2s.gestionbackend.dto.teams.mapper;

import com.insy2s.gestionbackend.dto.managers.ManagerLiteDto;
import com.insy2s.gestionbackend.dto.skills.mapper.SkillMapper;
import com.insy2s.gestionbackend.dto.teams.TeamDto;
import com.insy2s.gestionbackend.dto.teams.TeamWithSkillsAndManagersDto;
import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.model.Team;
import com.insy2s.gestionbackend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TeamMapper {

    private final SkillMapper skillMapper;
    private final TeamRepository teamRepository;

    public TeamWithSkillsAndManagersDto convertToDto(Team team) {

        int memberCount = teamRepository.findMemberCountByTeamId(team.getId());

        TeamWithSkillsAndManagersDto.TeamWithSkillsAndManagersDtoBuilder teamDtoBuilder = TeamWithSkillsAndManagersDto.builder()
                .id(team.getId())
                .name(team.getName())
                .projectStartDate(team.getProjectStartDate())
                .projectEndDate(team.getProjectEndDate())
                .isWeekEven(team.getIsWeekEven())
                .urlRepository(team.getUrlRepository())
                .urlBacklog(team.getUrlBacklog())
                .skills(team.getSkills().stream().map(skillMapper::convertToDto).collect(Collectors.toSet()))
                .projectState(calculateProjectState(team.getProjectEndDate()))
                .memberCount(memberCount)
                .managers(team.getManagers().stream().map(manager -> ManagerLiteDto.builder()
                        .id(manager.getId())
                        .lastName(manager.getLastName())
                        .firstName(manager.getFirstName())
                        .build()).collect(Collectors.toSet()));

        return teamDtoBuilder.build();
    }

    public Team convertToEntity(TeamWithSkillsAndManagersDto teamWithSkillsAndManagersDto) {
        return Team.builder()
                .id(teamWithSkillsAndManagersDto.getId())
                .name(teamWithSkillsAndManagersDto.getName())
                .projectStartDate(teamWithSkillsAndManagersDto.getProjectStartDate())
                .projectEndDate(teamWithSkillsAndManagersDto.getProjectEndDate())
                .isWeekEven(teamWithSkillsAndManagersDto.getIsWeekEven())
                .urlRepository(teamWithSkillsAndManagersDto.getUrlRepository())
                .urlBacklog(teamWithSkillsAndManagersDto.getUrlBacklog())
                .skills(teamWithSkillsAndManagersDto.getSkills().stream().map(skillMapper::convertToEntity).collect(Collectors.toSet()))
                .managers(teamWithSkillsAndManagersDto.getManagers().stream().map(manager -> Manager.builder()
                        .id(manager.getId())
                        .lastName(manager.getLastName())
                        .firstName(manager.getFirstName())
                        .build()).collect(Collectors.toSet()))
                .build();
    }

    private String calculateProjectState(Date projectEndDate) {
        if (projectEndDate == null) {
            return "N/A";
        }
        Date currentDate = new Date();
        if (currentDate.after(projectEndDate)) {
            long daysDiff = (currentDate.getTime() - projectEndDate.getTime()) / (1000 * 60 * 60 * 24);
            return "j+" + daysDiff;
        } else {
            long daysDiff = (projectEndDate.getTime() - currentDate.getTime()) / (1000 * 60 * 60 * 24);
            return "j-" + daysDiff;
        }
    }



    public static TeamDto TeamToTeamDto(Team team){
        return TeamDto.builder()
                .name(team.getName())
                .projectStartDate(team.getProjectStartDate())
                .projectEndDate(team.getProjectEndDate())
                .isWeekEven(team.getIsWeekEven())
                .urlRepository(team.getUrlRepository())
                .urlBacklog(team.getUrlBacklog())
                .skills(team.getSkills())
                .build();
    }

    public static Team TeamDtoToTeam(TeamDto teamDto){

            return Team.builder()
                    .name(teamDto.getName())
                    .projectStartDate(teamDto.getProjectStartDate())
                    .projectEndDate(teamDto.getProjectEndDate())
                    .isWeekEven(teamDto.getIsWeekEven())
                    .urlRepository(teamDto.getUrlRepository())
                    .urlBacklog(teamDto.getUrlBacklog())
                    .skills(teamDto.getSkills())
                    .build();
    }

}

