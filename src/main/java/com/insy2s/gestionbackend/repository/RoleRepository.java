package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
