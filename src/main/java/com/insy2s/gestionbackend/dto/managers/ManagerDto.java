package com.insy2s.gestionbackend.dto.managers;

import com.insy2s.gestionbackend.model.Meeting;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class ManagerDto {
    private Long id;
    private String email;
    private String lastName;
    private String firstName;
    private Boolean isDeleted;
    private Boolean isValidated;
    private String phoneNumber;
    private String roleName;
    private Set<Meeting> meetings;
}
