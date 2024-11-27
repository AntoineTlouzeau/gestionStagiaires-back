package com.insy2s.gestionbackend.utils;

import com.insy2s.gestionbackend.model.Intern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CsvImportResult {

    private List<Intern> validInterns;
    private List<String> errors;


}
