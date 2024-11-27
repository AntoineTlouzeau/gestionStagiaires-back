package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.dto.EmailDTO;
import com.insy2s.gestionbackend.dto.managers.ManagerDto;
import com.insy2s.gestionbackend.dto.managers.ManagerLiteDto;
import com.insy2s.gestionbackend.dto.managers.ManagerSkillDto;
import com.insy2s.gestionbackend.dto.managers.ManagerWithSkillsDto;
import com.insy2s.gestionbackend.dto.managers.mapper.ManagerLiteMapper;
import com.insy2s.gestionbackend.dto.managers.mapper.ManagerMapper;
import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.service.ManagerService;
import com.insy2s.gestionbackend.service.ManagerSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerMapper managerMapper;
    private final ManagerLiteMapper managerLiteMapper;
    private final ManagerSkillService managerSkillService;

    @PostMapping("/profil")
    public ResponseEntity<ManagerWithSkillsDto> getManagerByEmail(
            @RequestBody EmailDTO emailDTO) {
        String email = emailDTO.getEmail();
        ManagerWithSkillsDto managerWithSkillsDto = managerService.getManagerByEmail(email);
        if (managerWithSkillsDto != null) {
            return new ResponseEntity<>(managerWithSkillsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        boolean deleted = managerService.deleteManager(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<ManagerLiteDto> getAllManagersLite() {
        List<Manager> managers = managerService.getAllManagersLite();
        return managers.stream()
                .map(managerLiteMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/new")
    public ResponseEntity<ManagerDto> newManager(@RequestBody ManagerDto managerDto){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.newManager(managerDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ManagerDto>> getAllManagers(){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.getAllManagers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getManagerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.getManagerById(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<ManagerDto> updateManagerById(@RequestBody ManagerDto managerDto){
        return ResponseEntity.status(HttpStatus.OK).body(managerService.updateManagerById(managerDto));
    }

    @PostMapping("/add-skill")
    public ResponseEntity<ManagerSkillDto> addSkill(@RequestBody ManagerSkillDto managerSkillDto){
        return ResponseEntity.status(HttpStatus.OK).body(managerSkillService.newManagerSkill(managerSkillDto));
    }

    @GetMapping("/remove-skill")
    public ResponseEntity<ManagerSkillDto> removeSkill(@RequestBody Long id, String skillName){
        return ResponseEntity.status(HttpStatus.OK).body(managerSkillService.deleteManagerSkill(id, skillName));
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<ManagerSkillDto>> getSkillsByManagerId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(managerSkillService.getSkillsByManagerId(id));
    }
}
