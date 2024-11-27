package com.insy2s.gestionbackend.dto.teams.mapper;

import com.insy2s.gestionbackend.dto.teams.TeamLiteDto;
import com.insy2s.gestionbackend.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TeamLiteMapper {

            public TeamLiteDto convertToDto(Team team) {
                return TeamLiteDto.builder()
                        .id(team.getId())
                        .name(team.getName())
                        .startDate(team.getProjectStartDate())
                        .endDate(team.getProjectEndDate())
                        .isWeekEven(team.getIsWeekEven())
                        .urlRepository(team.getUrlRepository())
                        .urlBacklog(team.getUrlBacklog())
                        .build();
            }

            public static Team convertToEntity(TeamLiteDto teamDto) {
                return Team.builder()
                        .id(teamDto.getId())
                        .name(teamDto.getName())
                        .projectStartDate(teamDto.getStartDate())
                        .projectEndDate(teamDto.getEndDate())
                        .isWeekEven(teamDto.getIsWeekEven())
                        .urlRepository(teamDto.getUrlRepository())
                        .urlBacklog(teamDto.getUrlBacklog())
                        .build();
            }
}
