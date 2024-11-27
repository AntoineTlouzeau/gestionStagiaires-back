package com.insy2s.gestionbackend.dto.managers;

import com.insy2s.gestionbackend.dto.skills.SkillWithLevelDto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerWithSkillsDto {

    private Long id;
    private String email;
    private String lastName;
    private String firstName;
    private Boolean isValidated;
    private String phoneNumber;
    private String roleName;

    private List<SkillWithLevelDto> skillsWithLevel;
}