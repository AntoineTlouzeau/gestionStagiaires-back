package com.insy2s.gestionbackend.dto.teams;

import com.insy2s.gestionbackend.dto.managers.ManagerLiteDto;
import com.insy2s.gestionbackend.dto.skills.SkillDto;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamWithSkillsAndManagersDto {
    private Long id;
    private String name;
    private Date projectStartDate;
    private Date projectEndDate;
    private Boolean isWeekEven;
    private String urlRepository;
    private String urlBacklog;
    private Set<String> managerNames;
    private Integer memberCount;
    private String projectState;

    private Set<SkillDto> skills;
    private Set<ManagerLiteDto> managers;
}
