package com.insy2s.gestionbackend.dto.interns;

import com.insy2s.gestionbackend.customenum.PresenceType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternWithSkillsAndTeamsDto {
    private Long id;
    private String email;
    private String lastName;
    private String firstName;
    private PresenceType presenceType;
    private String trainings;
    private Boolean isDeleted;
    private String urlCv;
    private String hiredBy;
    private Date hiredAt;
    private String phoneNumber;

    private List<InternSkillDto> skills;
    private List<InternTeamDto> teams;
}
