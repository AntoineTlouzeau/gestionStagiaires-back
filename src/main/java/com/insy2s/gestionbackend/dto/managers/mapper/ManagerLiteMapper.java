package com.insy2s.gestionbackend.dto.managers.mapper;

import com.insy2s.gestionbackend.dto.managers.ManagerLiteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ManagerLiteMapper {

        public ManagerLiteDto convertToDto(com.insy2s.gestionbackend.model.Manager manager) {
            return ManagerLiteDto.builder()
                    .id(manager.getId())
                    .lastName(manager.getLastName())
                    .firstName(manager.getFirstName())
                    .build();
        }

        public static com.insy2s.gestionbackend.model.Manager convertToEntity(ManagerLiteDto managerDto) {
            return com.insy2s.gestionbackend.model.Manager.builder()
                    .id(managerDto.getId())
                    .lastName(managerDto.getLastName())
                    .firstName(managerDto.getFirstName())
                    .build();
        }
}
