package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.customenum.PresenceType;
import com.insy2s.gestionbackend.dto.interns.*;
import com.insy2s.gestionbackend.dto.interns.mapper.InternLiteMapper;
import com.insy2s.gestionbackend.dto.interns.mapper.InternMapper;
import com.insy2s.gestionbackend.model.Intern;
import com.insy2s.gestionbackend.model.InternTeam;
import com.insy2s.gestionbackend.service.InternService;
import com.insy2s.gestionbackend.service.InternSkillService;
import com.insy2s.gestionbackend.service.InternTeamService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interns")
public class InternController {

    private final InternService internService;
    private final InternSkillService internSkillService;
    private final InternTeamService internTeamService;
    private final InternMapper internMapper;
    private final InternLiteMapper internLiteMapper;

    private static final Logger logger = LoggerFactory.getLogger(InternController.class);

    @GetMapping
    public ResponseEntity<Page<InternWithSkillsAndTeamsDto>> getAllInternsWithPaginationAndFilter(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) PresenceType presenceType,
            @RequestParam(required = false) String skillId,
            @RequestParam(required = false) Long teamId,
            Pageable pageable) {
        Page<InternWithSkillsAndTeamsDto> internPage = internService.getAllInternsWithPaginationAndFilter(
                lastName,
                firstName,
                presenceType,
                skillId,
                teamId,
                pageable
        );
        return ResponseEntity.status(HttpStatus.OK).body(internPage);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<InternSkillDto>> getSkillsByInternId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(internSkillService.getSkillsByInternId(id));
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<InternTeamDto>> getTeamsByInternId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(internTeamService.getTeamsByInternId(id));
    }

    @GetMapping("/all-lite")
    public List<InternLiteDto> getAllInternsLite() {
        List<Intern> interns = internService.getAllInternsLite();
        return interns.stream()
                .map(internLiteMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{internId}/{teamId}")
    public ResponseEntity<InternLiteDto> getInternByIdWithTeamDates(@PathVariable Long internId, @PathVariable Long teamId) {
        Intern intern = internService.getInternByIdLite(internId);
        if (intern == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InternTeam internTeam = internTeamService.getInternTeamByInternAndTeamId(internId, teamId);
        InternLiteDto internLiteDto = internLiteMapper.convertToDto(intern);
        if (internTeam != null) {
            internLiteDto.setStartDate(internTeam.getStartDate());
            internLiteDto.setEndDate(internTeam.getEndDate());
        }
        return new ResponseEntity<>(internLiteDto, HttpStatus.OK);
    }

    @PutMapping("/modifyInternDates/{internId}/{teamId}")
    public ResponseEntity<InternLiteDto> modifyInternDates(@PathVariable Long internId, @PathVariable Long teamId, @RequestBody InternLiteDto internLiteDto) {
        Intern intern = internService.getInternByIdLite(internId);
        if (intern == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        InternTeam internTeam = internTeamService.getInternTeamByInternAndTeamId(internId, teamId);
        if (internTeam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        internTeam.setStartDate(internLiteDto.getStartDate());
        internTeam.setEndDate(internLiteDto.getEndDate());
        internTeamService.saveInternTeam(internTeam);
        return new ResponseEntity<>(internLiteDto, HttpStatus.OK);
    }


    // new methods to fix:
    @PostMapping("/new")
    public ResponseEntity<InternDto> newIntern(@RequestBody InternDto internDto) {
        return ResponseEntity.status(HttpStatus.OK).body(internService.newIntern(internDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InternDto>> getAllInterns() {
        return ResponseEntity.status(HttpStatus.OK).body(internService.getAllInterns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternDto> getInternById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(internService.getInternById(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<InternDto> updateInternById(@RequestBody InternDto internDto) {
        return ResponseEntity.status(HttpStatus.OK).body(internService.updateInternById(internDto));
    }

    @PostMapping("/add-skill")
    public ResponseEntity<InternSkillDto> addSkill(@RequestBody InternSkillDto internSkillDto) {
        return ResponseEntity.status(HttpStatus.OK).body(internSkillService.newInternSkill(internSkillDto));
    }

    @GetMapping("/remove-skill")
    public ResponseEntity<InternSkillDto> removeSkill(@RequestBody Long id, String skillName) {
        return ResponseEntity.status(HttpStatus.OK).body(internSkillService.deleteInternSkill(id, skillName));
    }
    ////////////////////////////

    @PostMapping
    public ResponseEntity<InternDto> createIntern(@ModelAttribute InternDto internDto) {
        logger.info("Receiving createIntern request with data: id=" + internDto.getId() + ", email=" + internDto.getEmail() + ", lastName=" + internDto.getLastName() + ", firstName=" + internDto.getFirstName() + ", presenceType=" + internDto.getPresenceType() + ", trainings=" + internDto.getTrainings() + ", isDeleted=" + internDto.getIsDeleted() + ", urlCv=" + internDto.getUrlCv() + ", hiredBy=" + internDto.getHiredBy() + ", hiredAt=" + internDto.getHiredAt() + ", phoneNumber=" + internDto.getPhoneNumber());
        InternDto internDtoCreated = internService.createIntern(internDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(internDtoCreated);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadCv(@RequestParam("cv") MultipartFile file, @RequestParam("internId") Long internId) {
        logger.info("Receiving uploadCv request with internId=" + internId + ", file original name=" + file.getOriginalFilename() + ", file size=" + file.getSize() + " bytes");
        try {
            internService.storeCv(file, internId);
            return ResponseEntity.ok().body("CV uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload CV");
        }
    }
    @PostMapping("/import-csv")
    public ResponseEntity<?> importCsvFile(@RequestParam("file") MultipartFile file) {
        try {
            Reader reader = new InputStreamReader(file.getInputStream());

            List<String> responseMessages = internService.importCsvFile(reader);

            if (responseMessages.contains("Importation réussie !")) {
                return new ResponseEntity<>(responseMessages, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(responseMessages, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Échec de l'importation : ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of("Échec de l'importation"));
        }
    }

}
