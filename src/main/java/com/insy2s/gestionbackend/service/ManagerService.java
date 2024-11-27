package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.dto.managers.ManagerDto;
import com.insy2s.gestionbackend.dto.managers.ManagerWithSkillsDto;
import com.insy2s.gestionbackend.model.Manager;

import java.util.List;

public interface ManagerService {

    ManagerWithSkillsDto getManagerByEmail(String email);
    boolean deleteManager(Long id);
    List<Manager> getAllManagersLite();
    List<ManagerDto> getAllManagers();
    ManagerDto newManager(ManagerDto managerDto);
    ManagerDto getManagerById(Long id);
    ManagerDto updateManagerById(ManagerDto updatedManager);
}
