package com.insy2s.gestionbackend.service.impl;

import com.insy2s.gestionbackend.model.Role;
import com.insy2s.gestionbackend.repository.RoleRepository;
import com.insy2s.gestionbackend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role newRole(Role role){
        return repository.save(role);
    }

    @Override
    public List<Role> getAllRoles(){
        return repository.findAll();
    }

    @Override
    public Role getRoleByName(String roleName){
        return repository.findById(roleName).orElse(null);
    }
}
