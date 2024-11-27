package com.insy2s.gestionbackend.dto.managers;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLiteDto {
    private Long id;
    private String lastName;
    private String firstName;
}
