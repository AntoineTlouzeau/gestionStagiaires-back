package com.insy2s.gestionbackend.dto.interns.mapper;

import com.insy2s.gestionbackend.dto.interns.InternLiteDto;
import com.insy2s.gestionbackend.model.Intern;
import org.springframework.stereotype.Component;

@Component
public class InternLiteMapper {

    public InternLiteDto convertToDto(Intern inter) {
        return InternLiteDto.builder()
                .id(inter.getId())
                .lastName(inter.getLastName())
                .firstName(inter.getFirstName())
                .build();
    }

    public static Intern convertToEntity(InternLiteDto interDto) {
        return Intern.builder()
                .id(interDto.getId())
                .lastName(interDto.getLastName())
                .firstName(interDto.getFirstName())
                .build();
    }
}
