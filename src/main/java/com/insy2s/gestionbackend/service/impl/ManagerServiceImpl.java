package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.dto.managers.ManagerDto;
import com.insy2s.gestionbackend.dto.managers.ManagerWithSkillsDto;
import com.insy2s.gestionbackend.dto.managers.mapper.ManagerMapper;
import com.insy2s.gestionbackend.dto.skills.SkillWithLevelDto;
import com.insy2s.gestionbackend.errors.ResourceNotFoundException;
import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.repository.ManagerRepository;
import com.insy2s.gestionbackend.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl  implements ManagerService {

    private final ManagerRepository managerRepository;
    @Override
    public ManagerWithSkillsDto getManagerByEmail(String email) {
        Manager manager = managerRepository.findByEmailIgnoreCase(email);
        if (manager == null) {
            throw new ResourceNotFoundException("Aucun manager trouvé avec l'email: " + email);
        }
        List<SkillWithLevelDto> skillsWithLevel = managerRepository.findSkillsById(manager.getId())
                .stream()
                .map(skill -> new SkillWithLevelDto(skill.getSkill().getSkillName(), skill.getLevel()))
                .toList();
        return ManagerMapper.convertToDto(manager, skillsWithLevel);
    }

    @Override
    public boolean deleteManager(Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Manager> getAllManagersLite() {
        Sort sort = Sort.by(Sort.Order.asc("firstName"));
        return managerRepository.findAll(sort);
    }

    @Override
    public ManagerDto newManager(ManagerDto managerDto) {
        Manager manager = ManagerMapper.ManagerDtoToManager(managerDto);

        if(manager != null){
            //TODO: manager password handling
            manager.setPassword("dev");
            manager.setSalt("dev");
            //TODO: manager deletion
            manager.setIsDeleted(false);
            //TODO: manager validation
            manager.setIsValidated(true);

            return ManagerMapper.ManagerToManagerDto(managerRepository.save(manager));
        }

        return managerDto;
    }

    @Override
    public List<ManagerDto> getAllManagers(){
        return managerRepository.findAll().stream().map(ManagerMapper::ManagerToManagerDto).collect(Collectors.toList());
    }

    @Override
    public ManagerDto getManagerById(Long id) {
        Optional<Manager> manager = managerRepository.findById(id);

        return manager.map(ManagerMapper::ManagerToManagerDto).orElse(null);
    }

    @Override
    public ManagerDto updateManagerById(ManagerDto updatedManager) {
        if(updatedManager.getId() == null) return updatedManager;

        Manager oldManager = managerRepository.findById(updatedManager.getId()).orElse(null);

        Manager manager = oldManager != null? ManagerMapper.ManagerToUpdatedManager(oldManager, updatedManager) : null;

        return manager != null? ManagerMapper.ManagerToManagerDto(managerRepository.save(manager)) : updatedManager;
    }
}
