package com.insy2s.gestionbackend.repository;

import com.insy2s.gestionbackend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    @Query("SELECT COUNT(it) FROM InternTeam it WHERE it.id.teamId = :teamId")
    int findMemberCountByTeamId(Long teamId);

    List<Team> findByIsWeekEven(Boolean isWeekEven);

    List<Team> findByNameContainingIgnoreCase(String name);
}
