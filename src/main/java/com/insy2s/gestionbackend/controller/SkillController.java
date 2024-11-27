package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.dto.interns.InternSkillDto;
import com.insy2s.gestionbackend.dto.managers.ManagerSkillDto;
import com.insy2s.gestionbackend.dto.skills.SkillDto;
import com.insy2s.gestionbackend.dto.skills.mapper.SkillMapper;
import com.insy2s.gestionbackend.model.Skill;
import com.insy2s.gestionbackend.service.InternSkillService;
import com.insy2s.gestionbackend.service.ManagerSkillService;
import com.insy2s.gestionbackend.service.SkillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/skills")
public class SkillController {

    private final SkillMapper skillMapper;
    private final SkillService skillService;
    private final InternSkillService internSkillService;
    private final ManagerSkillService managerSkillService;

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        Skill skill = skillMapper.convertToEntity(skillDto);
        Skill createdSkill = skillService.createSkill(skill);
        return new ResponseEntity<>(skillMapper.convertToDto(createdSkill), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills() {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.getAllSkills());
    }

    @PostMapping("/new")
    public ResponseEntity<Skill> newSkill(@RequestBody Skill skill) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.newSkill(skill));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Skill> getSkillByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(skillService.getSkillByName(name));
    }

    @GetMapping("/{name}/interns")
    public ResponseEntity<List<InternSkillDto>> getInternsBySkill(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(internSkillService.getInternsBySkill(name));
    }

    @GetMapping("/{name}/managers")
    public ResponseEntity<List<ManagerSkillDto>> getManagersBySkill(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(managerSkillService.getManagersBySkill(name));
    }
}