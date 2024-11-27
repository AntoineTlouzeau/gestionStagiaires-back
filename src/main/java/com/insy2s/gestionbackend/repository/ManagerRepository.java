package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.Manager;
import com.insy2s.gestionbackend.model.ManagerSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("SELECT m FROM Manager m WHERE LOWER(m.email) = LOWER(:email)")
    Manager findByEmailIgnoreCase(String email);

    @Query("SELECT ms FROM ManagerSkill ms WHERE ms.manager.id = :managerId")
    List<ManagerSkill> findSkillsById(@Param("managerId") Long managerId);
}