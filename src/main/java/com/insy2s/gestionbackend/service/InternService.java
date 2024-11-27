package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.customenum.PresenceType;
import com.insy2s.gestionbackend.dto.interns.InternDto;
import com.insy2s.gestionbackend.dto.interns.InternWithSkillsAndTeamsDto;
import com.insy2s.gestionbackend.errors.intern.EmailAlreadyExistsException;
import com.insy2s.gestionbackend.model.Intern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;

public interface InternService {
    Page<InternWithSkillsAndTeamsDto> getAllInternsWithPaginationAndFilter(
            String lastName,
            String firstName,
            PresenceType presenceType,
            String skillId,
            Long teamId,
            Pageable pageable);
    List<Intern> getAllInternsLite();
    Intern getInternByIdLite(Long id);
    InternDto newIntern(InternDto internDto);
    List<InternDto> getAllInterns();
    InternDto getInternById(Long id);
    InternDto updateInternById(InternDto updatedIntern);

    InternDto createIntern(InternDto internDto) throws EmailAlreadyExistsException; ;
    void storeCv(MultipartFile file, Long internId);
    List<String> importCsvFile(Reader reader) throws Exception;
}
