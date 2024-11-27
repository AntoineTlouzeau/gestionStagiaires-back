package com.insy2s.gestionbackend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;
}
