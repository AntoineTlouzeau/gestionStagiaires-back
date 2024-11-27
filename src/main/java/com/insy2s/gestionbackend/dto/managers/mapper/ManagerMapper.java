package com.insy2s.gestionbackend.dto.managers.mapper;

import com.insy2s.gestionbackend.dto.managers.ManagerDto;
import com.insy2s.gestionbackend.dto.managers.ManagerWithSkillsDto;
import com.insy2s.gestionbackend.dto.skills.SkillWithLevelDto;
import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ManagerMapper {

    public static ManagerWithSkillsDto convertToDto(Manager manager, List<SkillWithLevelDto> skillsWithLevel) {
        return ManagerWithSkillsDto.builder()
                .id(manager.getId())
                .email(manager.getEmail())
                .lastName(manager.getLastName())
                .firstName(manager.getFirstName())
                .isValidated(manager.getIsValidated())
                .phoneNumber(manager.getPhoneNumber())
                .roleName(manager.getRoleName() != null ? manager.getRoleName().getRoleName() : null)
                .skillsWithLevel(skillsWithLevel)
                .build();
    }

    public static Manager convertToEntity(ManagerWithSkillsDto managerWithSkillsDto) {
        return Manager.builder()
                .id(managerWithSkillsDto.getId())
                .email(managerWithSkillsDto.getEmail())
                .lastName(managerWithSkillsDto.getLastName())
                .firstName(managerWithSkillsDto.getFirstName())
                .isValidated(managerWithSkillsDto.getIsValidated())
                .phoneNumber(managerWithSkillsDto.getPhoneNumber())
                .build();
    }
    public static ManagerDto ManagerToManagerDto(Manager manager){
        return ManagerDto.builder()
                .id(manager.getId())
                .email(manager.getEmail())
                .lastName(manager.getLastName())
                .firstName(manager.getFirstName())
                .isDeleted(manager.getIsDeleted())
                .isValidated(manager.getIsValidated())
                .phoneNumber(manager.getPhoneNumber())
                .roleName(manager.getRoleName().getRoleName())
                .build();
    }

    public static Manager ManagerDtoToManager(ManagerDto managerDto){
        return Manager.builder()
                .email(managerDto.getEmail())
                .lastName(managerDto.getLastName())
                .firstName(managerDto.getFirstName())
                .isDeleted(managerDto.getIsDeleted())
                .isValidated(managerDto.getIsValidated())
                .phoneNumber(managerDto.getPhoneNumber())
                .roleName(new Role(managerDto.getRoleName()))
                .build();
    }

    public static Manager ManagerToUpdatedManager(Manager oldManager, ManagerDto updatedManager){
        return Manager.builder()
                .id(oldManager.getId())
                .email(updatedManager.getEmail() != null? updatedManager.getEmail() : oldManager.getEmail())
                .lastName(updatedManager.getLastName() != null? updatedManager.getLastName() : oldManager.getLastName())
                .firstName(updatedManager.getFirstName() != null? updatedManager.getFirstName() : oldManager.getFirstName())
                .password(oldManager.getPassword())
                .salt(oldManager.getSalt())
                .isDeleted(oldManager.getIsDeleted())
                .isValidated(oldManager.getIsValidated())
                .phoneNumber(updatedManager.getPhoneNumber() != null? updatedManager.getPhoneNumber() : oldManager.getPhoneNumber())
                .roleName(updatedManager.getRoleName() != null? new Role(updatedManager.getRoleName()) : oldManager.getRoleName())
                .meetings(oldManager.getMeetings())
                .build();
    }
}