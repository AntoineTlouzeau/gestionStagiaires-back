package com.insy2s.gestionbackend.service;

import com.insy2s.gestionbackend.model.Role;

import java.util.List;

public interface RoleService {

    Role newRole(Role role);
    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
}
