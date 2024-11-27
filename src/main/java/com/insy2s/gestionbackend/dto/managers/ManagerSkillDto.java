package com.insy2s.gestionbackend.dto.managers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ManagerSkillDto {
    Long managerId;
    String skill;
    Integer level;
}