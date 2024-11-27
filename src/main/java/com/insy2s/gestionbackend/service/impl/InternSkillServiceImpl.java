package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.dto.interns.InternSkillDto;
import com.insy2s.gestionbackend.dto.interns.mapper.InternSkillMapper;
import com.insy2s.gestionbackend.model.InternSkill;
import com.insy2s.gestionbackend.model.embedded.InternSkillId;
import com.insy2s.gestionbackend.repository.InternRepository;
import com.insy2s.gestionbackend.repository.InternSkillRepository;
import com.insy2s.gestionbackend.repository.SkillRepository;
import com.insy2s.gestionbackend.service.InternSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternSkillServiceImpl implements InternSkillService {
    private final InternSkillRepository internSkillRepository;
    private final InternRepository internRepository;
    private final SkillRepository skillRepository;

    @Override
    public InternSkillDto newInternSkill(InternSkillDto internSkillDto) {
        InternSkillMapper internSkillMapper = new InternSkillMapper(internRepository, skillRepository);

        InternSkill internSkill = internSkillMapper.convertToEntity(internSkillDto);
        InternSkillId internSkillId = new InternSkillId(internSkill.getIntern().getId(), internSkill.getSkill().getSkillName());

        internSkill.setId(internSkillId);

        return internSkillMapper.convertToDto(internSkillRepository.save(internSkill));
    }

    @Override
    public List<InternSkillDto> getSkillsByInternId(Long id){
        InternSkillMapper internSkillMapper = new InternSkillMapper(internRepository, skillRepository);

        return internSkillRepository.findAll()
                .stream()
                .filter(internSkill -> Objects.equals(internSkill.getIntern().getId(), id))
                .map(internSkillMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InternSkillDto> getInternsBySkill(String skillName){
        InternSkillMapper internSkillMapper = new InternSkillMapper(internRepository, skillRepository);

        return internSkillRepository.findAll()
                .stream()
                .filter(internSkill -> Objects.equals(internSkill.getSkill().getSkillName(), skillName))
                .map(internSkillMapper::convertToDto)
                .collect(Collectors.toList());
    }

    // fix this
    @Override
    public InternSkillDto deleteInternSkill(Long id, String skillName){

        InternSkillMapper internSkillMapper = new InternSkillMapper(internRepository, skillRepository);
        InternSkillId internSkillId = new InternSkillId(id, skillName);
        InternSkillDto internSkillDto = internSkillMapper.convertToDto(Objects.requireNonNull(internSkillRepository.findById(internSkillId).orElse(null)));
        internSkillRepository.deleteById(internSkillId);
        return internSkillDto;
    }
}
