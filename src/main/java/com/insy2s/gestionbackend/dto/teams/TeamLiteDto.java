package com.insy2s.gestionbackend.dto.teams;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamLiteDto {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Boolean isWeekEven;
    private String urlRepository;
    private String urlBacklog;
}
