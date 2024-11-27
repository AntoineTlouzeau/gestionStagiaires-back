package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.customenum.PresenceType;
import com.insy2s.gestionbackend.dto.interns.InternDto;
import com.insy2s.gestionbackend.dto.interns.InternWithSkillsAndTeamsDto;
import com.insy2s.gestionbackend.dto.interns.mapper.InternMapper;
import com.insy2s.gestionbackend.errors.InternalServerErrorException;
import com.insy2s.gestionbackend.errors.ResourceNotFoundException;
import com.insy2s.gestionbackend.errors.intern.EmailAlreadyExistsException;
import com.insy2s.gestionbackend.filter.InternSpecifications;
import com.insy2s.gestionbackend.model.Intern;
import com.insy2s.gestionbackend.repository.InternRepository;
import com.insy2s.gestionbackend.repository.InternTeamRepository;
import com.insy2s.gestionbackend.repository.SkillRepository;
import com.insy2s.gestionbackend.repository.TeamRepository;
import com.insy2s.gestionbackend.service.InternService;
import com.insy2s.gestionbackend.utils.CsvImport;
import com.insy2s.gestionbackend.utils.CsvImportResult;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternServiceImpl implements InternService {

    @Value("${cv.storage.path}")
    private String cvStoragePath;

    @Value("${log.storage.path}")
    private String logStoragePath;

    private final InternRepository internRepository;
    private final TeamRepository teamRepository;
    private final SkillRepository skillRepository;
    private final InternMapper internMapper;
    private final InternTeamRepository internTeamRepository;

    private CsvImport csvImport;
    private static final Logger logger = LoggerFactory.getLogger(InternServiceImpl.class);

    @Override
    public Page<InternWithSkillsAndTeamsDto> getAllInternsWithPaginationAndFilter
                (String lastName,
                String firstName,
                PresenceType presenceType,
                String skillId,
                Long teamId,
                Pageable pageable) {
        InternSpecifications internSpecifications = new InternSpecifications(skillRepository, teamRepository);
        try {
            Specification<Intern> specification = Specification.where(null);
            if (lastName != null) {
                specification = specification.and(internSpecifications.hasLastnameContaining(lastName));
            }
            if (firstName != null) {
                specification = specification.and(internSpecifications.hasFirstnameContaining(firstName));
            }
            if (presenceType != null) {
                specification = specification.and(internSpecifications.hasPresenceType(presenceType));
            }
            if (skillId != null) {
                specification = specification.and(internSpecifications.hasSkillId(skillId));
            }
            if (teamId != null) {
                specification = specification.and(internSpecifications.hasTeamId(teamId));
            }
            Page<Intern> internPage = internRepository.findAll(specification, pageable);
            List<InternWithSkillsAndTeamsDto> internWithSkillsAndTeamsDtos = new ArrayList<>();
            for (Intern intern : internPage.getContent()) {
                InternWithSkillsAndTeamsDto internWithSkillsAndTeamsDto = internMapper.convertToDto(intern);
                internWithSkillsAndTeamsDtos.add(internWithSkillsAndTeamsDto);
            }
            if (internWithSkillsAndTeamsDtos.isEmpty()) {
                throw new ResourceNotFoundException("No intern found");
            }
            return new PageImpl<>(internWithSkillsAndTeamsDtos, pageable, internPage.getTotalElements());
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Internal server error. Try again !");
        }
    }

    @Override
    public List<Intern> getAllInternsLite() {
        Sort sort = Sort.by(Sort.Order.asc("firstName"));
        return internRepository.findAll(sort);
    }

    @Override
    public Intern getInternByIdLite(Long id) {

        return internRepository.findById(id).orElse(null);
    }

    @Override
    public InternDto newIntern(InternDto internDto) {

        Intern intern = InternMapper.InternDtoToIntern(internDto);
        return intern != null? InternMapper.InternToInternDto(internRepository.save(intern)) : internDto;
    }

    @Override
    public List<InternDto> getAllInterns(){
        return internRepository.findAll().stream().map(InternMapper::InternToInternDto).collect(Collectors.toList());
    }

    @Override
    public InternDto getInternById(Long id) {

        Optional<Intern> intern = internRepository.findById(id);
        return intern.map(InternMapper::InternToInternDto).orElse(null);
    }

    @Override
    public InternDto updateInternById(InternDto updatedIntern) {

        if(updatedIntern.getId() == null) return updatedIntern;
        Intern oldIntern = internRepository.findById(updatedIntern.getId()).orElse(null);
        Intern intern = oldIntern != null? InternMapper.InternToUpdatedIntern(oldIntern, updatedIntern) : null;
        return intern != null? InternMapper.InternToInternDto(internRepository.save(intern)) : updatedIntern;
    }
    //////////////////

    @Override
    public InternDto createIntern(InternDto internDto) {
        logger.info("Processing intern creation with data: id=" + internDto.getId() + ", email=" + internDto.getEmail() + ", lastName=" + internDto.getLastName() + ", firstName=" + internDto.getFirstName() + ", presenceType=" + internDto.getPresenceType() + ", trainings=" + internDto.getTrainings() + ", isDeleted=" + internDto.getIsDeleted() + ", urlCv=" + internDto.getUrlCv() + ", hiredBy=" + internDto.getHiredBy() + ", hiredAt=" + internDto.getHiredAt() + ", phoneNumber=" + internDto.getPhoneNumber());
        Optional<Intern> existingIntern = internRepository.findByEmail(internDto.getEmail());
        if (existingIntern.isPresent()) {
            throw new EmailAlreadyExistsException("Un stagiaire avec cet email existe déjà.");
        }
        try {
            Intern intern = internMapper.InternDtoToIntern(internDto);
            Intern internCreated = internRepository.save(intern);
            return internMapper.InternToInternDto(internCreated);
        }
        catch (DataAccessException e) {
            throw new InternalServerErrorException("Oops, erreur interne du serveur. Try again !");
        }
    }

    @Override
    public void storeCv(MultipartFile file, Long internId) {
        logger.info("Processing CV storage for internId=" + internId + ", file original name=" + file.getOriginalFilename() + ", file size=" + file.getSize() + " bytes, destination path=" + cvStoragePath);
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        try {
            Intern intern = internRepository.findById(internId).orElseThrow(() -> new IllegalArgumentException("Intern not found"));
            String fileName = cvStoragePath + File.separator + internId + "_" + file.getOriginalFilename();
            Path path = Paths.get(fileName);
            System.out.println("Writing file to: " + path);
            Files.write(path, file.getBytes());
            intern.setUrlCv(fileName);
            internRepository.save(intern);
        } catch (IOException e) {
            System.err.println("Error while saving file: " + e.getMessage());
        }
    }

    @Override
    public List<String> importCsvFile(Reader reader) throws Exception {
        CsvImportResult result = CsvImport.readCsvFile(reader);
        List<String> finalErrors = new ArrayList<>(result.getErrors());
        List<String> responseMessages = new ArrayList<>();

        int lineCount = 1;

        for (Intern intern : result.getValidInterns()) {
            lineCount++;
            Optional<Intern> existingIntern = internRepository.findByEmail(intern.getEmail());
            if (existingIntern.isPresent()) {
                finalErrors.add("Stagiaire avec l'email " + intern.getEmail() + " existe déjà (ligne " + lineCount + "). Ignoré.");
                continue;
            }
            internRepository.save(intern);
        }

        if (finalErrors.isEmpty()) {
            responseMessages.add("Importation réussie !");
        } else {
            for (String error : finalErrors) {
                logger.error(error);
                responseMessages.add(error);
            }
        }

        String filename = logStoragePath + File.separator + "logs_" + System.currentTimeMillis() + ".txt";
        generateLogFile(responseMessages, filename);

        return responseMessages;
    }

    private void generateLogFile(List<String> responseMessages, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String message : responseMessages) {
                writer.write(message + System.lineSeparator());
            }
        }
    }
}