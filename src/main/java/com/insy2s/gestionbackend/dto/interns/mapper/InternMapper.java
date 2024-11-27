package com.insy2s.gestionbackend.dto.interns.mapper;

import com.insy2s.gestionbackend.dto.interns.InternDto;
import com.insy2s.gestionbackend.dto.interns.InternSkillDto;
import com.insy2s.gestionbackend.dto.interns.InternTeamDto;
import com.insy2s.gestionbackend.dto.interns.InternWithSkillsAndTeamsDto;
import com.insy2s.gestionbackend.model.Intern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InternMapper {

    private final InternSkillMapper internSkillMapper;
    private final InternTeamMapper internTeamMapper;

    public InternWithSkillsAndTeamsDto convertToDto(Intern intern) {
        InternWithSkillsAndTeamsDto.InternWithSkillsAndTeamsDtoBuilder internDtoBuilder = InternWithSkillsAndTeamsDto.builder()
                .id(intern.getId())
                .email(intern.getEmail())
                .lastName(intern.getLastName())
                .firstName(intern.getFirstName())
                .presenceType(intern.getPresenceType())
                .trainings(intern.getTrainings())
                .isDeleted(intern.getIsDeleted())
                .urlCv(intern.getUrlCv())
                .hiredBy(intern.getHiredBy())
                .hiredAt(intern.getHiredAt())
                .phoneNumber(intern.getPhoneNumber());

        List<InternSkillDto> internSkillDtos = intern.getInternSkills().stream()
                .map(internSkillMapper::convertToDto)
                .collect(Collectors.toList());

        List<InternTeamDto> internTeamDtos = intern.getInternTeams().stream()
                .map(internTeamMapper::convertToDto)
                .collect(Collectors.toList());

        return internDtoBuilder
                .skills(internSkillDtos)
                .teams(internTeamDtos)
                .build();
    }

    public static InternDto InternToInternDto(Intern intern){
        return InternDto.builder()
                .id(intern.getId())
                .email(intern.getEmail())
                .lastName(intern.getLastName())
                .firstName(intern.getFirstName())
                .presenceType(intern.getPresenceType())
                .trainings(intern.getTrainings())
                .isDeleted(intern.getIsDeleted())
                .urlCv(intern.getUrlCv())
                .hiredBy(intern.getHiredBy())
                .hiredAt(intern.getHiredAt() != null?intern.getHiredAt().toString():null)
                .phoneNumber(intern.getPhoneNumber())
                .build();
    }

    public static Intern InternDtoToIntern(InternDto internDto){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try{
            return Intern.builder()
                    .email(internDto.getEmail())
                    .lastName(internDto.getLastName())
                    .firstName(internDto.getFirstName())
                    .presenceType(internDto.getPresenceType())
                    .trainings(internDto.getTrainings())
                    .isDeleted(internDto.getIsDeleted())
                    .urlCv(internDto.getUrlCv())
                    .hiredBy(internDto.getHiredBy())
                    .hiredAt(internDto.getHiredAt() != null ?formatter.parse(internDto.getHiredAt()):null)
                    .phoneNumber(internDto.getPhoneNumber())
                    .build();
        } catch (ParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Intern InternToUpdatedIntern(Intern oldIntern, InternDto updatedIntern){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        try{
            return Intern.builder()
                    .id(oldIntern.getId())
                    .email(updatedIntern.getEmail() != null? updatedIntern.getEmail() : oldIntern.getEmail())
                    .lastName(updatedIntern.getLastName() != null? updatedIntern.getLastName() : oldIntern.getLastName())
                    .firstName(updatedIntern.getFirstName() != null? updatedIntern.getFirstName() : oldIntern.getFirstName())
                    .presenceType(updatedIntern.getPresenceType() != null? updatedIntern.getPresenceType() : oldIntern.getPresenceType())
                    .trainings(updatedIntern.getTrainings() != null? updatedIntern.getTrainings() : oldIntern.getTrainings())
                    .isDeleted(updatedIntern.getIsDeleted() != null? updatedIntern.getIsDeleted() : oldIntern.getIsDeleted())
                    .urlCv(updatedIntern.getUrlCv() != null? updatedIntern.getUrlCv() : oldIntern.getUrlCv())
                    .hiredBy(updatedIntern.getHiredBy() != null? updatedIntern.getHiredBy() : oldIntern.getHiredBy())
                    .hiredAt(updatedIntern.getHiredAt() != null? formatter.parse(updatedIntern.getHiredAt()) : oldIntern.getHiredAt())
                    .phoneNumber(updatedIntern.getPhoneNumber() != null? updatedIntern.getPhoneNumber() : oldIntern.getPhoneNumber())
                    .build();
        } catch (ParseException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
