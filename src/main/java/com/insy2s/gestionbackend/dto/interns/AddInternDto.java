package com.insy2s.gestionbackend.dto.interns;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInternDto {
    @NotNull(message = "Start date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

    @AssertTrue(message = "End date must be after the start date")
    public boolean isDateValid() {
        return startDate != null && endDate != null && endDate.isAfter(startDate);
    }
}