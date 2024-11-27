package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.Intern;
import com.insy2s.gestionbackend.model.InternTeam;
import com.insy2s.gestionbackend.model.embedded.InternTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternTeamRepository extends JpaRepository<InternTeam, InternTeamId> {
    InternTeam findByIdInternId(Long internId);
        
    boolean existsByInternIdAndTeamId(Long internId, Long teamId);

    @Query("select it.intern from InternTeam it where it.team.id = :teamId")
    List<Intern> findInternsByTeamId(@Param("teamId") Long teamId);

    @Query("SELECT it FROM InternTeam it WHERE it.id.internId = :internId AND it.id.teamId = :teamId")
    Optional<InternTeam> findByIdInternAndIdTeam(@Param("internId") Long internId, @Param("teamId") Long teamId);
}