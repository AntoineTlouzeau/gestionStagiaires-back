package com.insy2s.gestionbackend.controller;

import com.insy2s.gestionbackend.model.Role;
import com.insy2s.gestionbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/new")
    public ResponseEntity<Role> newRole(@RequestBody Role role){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.newRole(role));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleByName(name));
    }
}
