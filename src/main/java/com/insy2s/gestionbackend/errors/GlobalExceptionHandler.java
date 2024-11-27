package com.insy2s.gestionbackend.errors;

import com.insy2s.gestionbackend.dto.ErrorDto;
import com.insy2s.gestionbackend.errors.intern.EmailAlreadyExistsException;
import com.insy2s.gestionbackend.errors.intern.InternAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.intern.InternNotAssociatedException;
import com.insy2s.gestionbackend.errors.intern.InternNotFoundException;
import com.insy2s.gestionbackend.errors.manager.ManagerAlreadyInTeamException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotAssociatedException;
import com.insy2s.gestionbackend.errors.manager.ManagerNotFoundException;
import com.insy2s.gestionbackend.errors.skill.SkillAlreadyExistsException;
import com.insy2s.gestionbackend.errors.skill.SkillNotAssociatedException;
import com.insy2s.gestionbackend.errors.skill.SkillNotFoundException;
import com.insy2s.gestionbackend.errors.team.TeamAlreadyExistsException;
import com.insy2s.gestionbackend.errors.team.TeamAlreadyUpdatedException;
import com.insy2s.gestionbackend.errors.team.TeamNotFoundException;
import com.insy2s.gestionbackend.errors.team.TeamUpdateFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for ResourceNotFoundException.
     *
     * @param ex The ResourceNotFoundException instance to be handled.
     * @return ResponseEntity containing an ErrorDto with the error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }


    @ExceptionHandler(SkillAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleSkillAlreadyExistsException(SkillAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(SkillNotAssociatedException.class)
    public ResponseEntity<ErrorDto> handleSkillNotAssociatedException(SkillNotAssociatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTeamNotFoundException(TeamNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(InternNotFoundException.class)
    public ResponseEntity<ErrorDto> handleInternNotFoundException(InternNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Extracting the binding result to get all errors
        BindingResult bindingResult = ex.getBindingResult();

        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String defaultMessage = fieldError.getDefaultMessage();
                    return String.format("Field '%s' has error: %s", field, defaultMessage);
                })
                .collect(Collectors.joining("; "));

        return ResponseEntity.badRequest()
                .body(ErrorDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(errorMessage)
                        .timestamp(LocalDateTime.now())
                        .build());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<ErrorDto> handleManagerNotFoundException(ManagerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(SkillNotFoundException.class)
    public ResponseEntity<ErrorDto> handleSkillNotFoundException(SkillNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(InternAlreadyInTeamException.class)
    public ResponseEntity<ErrorDto> handleInternAlreadyInTeamException(InternAlreadyInTeamException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ManagerAlreadyInTeamException.class)
    public ResponseEntity<ErrorDto> handleManagerAlreadyInTeamException(ManagerAlreadyInTeamException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(ManagerNotAssociatedException.class)
    public ResponseEntity<ErrorDto> handleManagerNotAssociatedException(ManagerNotAssociatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(InternNotAssociatedException.class)
    public ResponseEntity<ErrorDto> handleInternNotAssociatedException(InternNotAssociatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(TeamAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleManagerAlreadyExistsException(TeamAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(TeamAlreadyUpdatedException.class)
    public ResponseEntity<ErrorDto> handleTeamAlreadyUpdatedException(TeamAlreadyUpdatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(TeamUpdateFailedException.class)
    public ResponseEntity<ErrorDto> handleTeamNotUpdatedException(TeamUpdateFailedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorDto.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}