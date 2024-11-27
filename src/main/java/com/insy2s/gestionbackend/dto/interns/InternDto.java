package com.insy2s.gestionbackend.dto.interns;

import com.insy2s.gestionbackend.customenum.PresenceType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternDto {
    private Long id;
    private String email;
    private String lastName;
    private String firstName;
    private PresenceType presenceType;
    private String trainings;
    private Boolean isDeleted;
    private String urlCv;
    private String hiredBy;
    private String hiredAt;
    private String phoneNumber;
}
