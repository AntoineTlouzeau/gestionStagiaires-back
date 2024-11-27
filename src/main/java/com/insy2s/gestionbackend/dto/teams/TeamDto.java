package com.insy2s.gestionbackend.dto.teams;

import com.insy2s.gestionbackend.model.Skill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
public class TeamDto {
    String name;
    Date projectStartDate;
    Date projectEndDate;
    Boolean isWeekEven;
    String urlRepository;
    String urlBacklog;
    Set<Skill> skills;
}
