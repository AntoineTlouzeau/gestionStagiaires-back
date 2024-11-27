package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.errors.skill.SkillAlreadyExistsException;
import com.insy2s.gestionbackend.model.Skill;
import com.insy2s.gestionbackend.repository.SkillRepository;
import com.insy2s.gestionbackend.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    // fix dobule getallskills
//    @Override
//    public List<Skill> getAllSkills() {
//        Sort sort = Sort.by(Sort.Order.asc("skillName"));
//        return skillRepository.findAll(sort);
//    }

    @Override
    public Skill createSkill(Skill skill) {
        if (skillRepository.findById(skill.getSkillName()).isPresent()) {
            throw new SkillAlreadyExistsException("Ce langage existe déjà en base de données.");
        }
        return skillRepository.save(skill);
    }

    @Override
    public Skill newSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillByName(String skillName) {
        return skillRepository.findById(skillName).orElse(null);
    }
}
