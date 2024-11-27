package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.dto.managers.ManagerSkillDto;
import com.insy2s.gestionbackend.dto.managers.mapper.ManagerSkillMapper;
import com.insy2s.gestionbackend.model.ManagerSkill;
import com.insy2s.gestionbackend.model.embedded.ManagerSkillId;
import com.insy2s.gestionbackend.repository.ManagerRepository;
import com.insy2s.gestionbackend.repository.ManagerSkillRepository;
import com.insy2s.gestionbackend.repository.SkillRepository;
import com.insy2s.gestionbackend.service.ManagerSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerSkillServiceImpl implements ManagerSkillService {
    private final ManagerSkillRepository managerSkillRepository;
    private final ManagerRepository managerRepository;
    private final SkillRepository skillRepository;

    @Override
    public ManagerSkillDto newManagerSkill(ManagerSkillDto managerSkillDto) {
        ManagerSkillMapper managerSkillMapper = new ManagerSkillMapper(managerRepository, skillRepository);

        ManagerSkill managerSkill = managerSkillMapper.managerSkillDtoToManagerSkill(managerSkillDto);
        ManagerSkillId managerSkillId = new ManagerSkillId(managerSkill.getManager().getId(), managerSkill.getSkill().getSkillName());

        managerSkill.setId(managerSkillId);

        return managerSkillMapper.managerSkillToManagerSkillDto(managerSkillRepository.save(managerSkill));
    }

    @Override
    public List<ManagerSkillDto> getSkillsByManagerId(Long id){
        ManagerSkillMapper managerSkillMapper = new ManagerSkillMapper(managerRepository, skillRepository);

        return managerSkillRepository.findAll()
                .stream()
                .filter(managerSkill -> Objects.equals(managerSkill.getManager().getId(), id))
                .map(managerSkillMapper::managerSkillToManagerSkillDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ManagerSkillDto> getManagersBySkill(String skillName){
        ManagerSkillMapper managerSkillMapper = new ManagerSkillMapper(managerRepository, skillRepository);

        return managerSkillRepository.findAll()
                .stream()
                .filter(managerSkill -> Objects.equals(managerSkill.getSkill().getSkillName(), skillName))
                .map(managerSkillMapper::managerSkillToManagerSkillDto)
                .collect(Collectors.toList());
    }

    @Override
    public ManagerSkillDto deleteManagerSkill(Long id, String skillName){
        ManagerSkillMapper managerSkillMapper = new ManagerSkillMapper(managerRepository, skillRepository);

        ManagerSkillId managerSkillId = new ManagerSkillId(id, skillName);

        ManagerSkillDto managerSkillDto = managerSkillMapper.managerSkillToManagerSkillDto(Objects.requireNonNull(managerSkillRepository.findById(managerSkillId).orElse(null)));
        managerSkillRepository.deleteById(managerSkillId);

        return managerSkillDto;
    }
}