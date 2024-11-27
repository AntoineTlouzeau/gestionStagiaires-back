package com.insy2s.gestionbackend.dto.interns;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternTeamDto {
    Long internId;
    Long teamId;
    Date startDate;
    Date endDate;
}
