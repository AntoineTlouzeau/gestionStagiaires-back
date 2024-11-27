package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.errors.InternalServerErrorException;
import com.insy2s.gestionbackend.errors.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/exception/runtime")
    public void runtimeException() {
        throw new RuntimeException("RuntimeException thrown manually");
    }

    @GetMapping("/exception/internalServer")
    public void internalServerException() {
        throw new InternalServerErrorException("InternalServerErrorException thrown manually");
    }

    @GetMapping("/exception/resourceNotFound")
    public void resourceNotFoundException() {
        throw new ResourceNotFoundException("ResourceNotFoundException thrown manually");
    }

    @GetMapping()
    public String testSuccess() {
        return "Test successful!";
    }
}