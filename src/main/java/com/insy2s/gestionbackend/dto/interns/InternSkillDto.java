package com.insy2s.gestionbackend.dto.interns;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternSkillDto {
    Long internId;
    String skill;
    Integer level;
}
