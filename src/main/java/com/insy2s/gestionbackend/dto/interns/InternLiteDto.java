package com.insy2s.gestionbackend.dto.interns;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternLiteDto implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    private Date startDate;
    private Date endDate;
}