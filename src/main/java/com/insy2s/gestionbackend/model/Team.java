package com.insy2s.gestionbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_team", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "project_start_date", nullable = false)
    private Date projectStartDate;

    @Column(name = "project_end_date")
    private Date projectEndDate;

    @Column(name = "is_week_even", nullable = false)
    private Boolean isWeekEven;

    @Column(name = "url_repository", nullable = false)
    private String urlRepository;

    @Column(name = "url_backlog", nullable = false)
    private String urlBacklog;

    //ERREUR SUR LE MAIN, LA RELATION SE FAIT AVEC JOIN COLUMNS!!!
    @ManyToMany
    @JoinTable(name = "team_skills",
            joinColumns = @JoinColumn(name = "team_id_team"),
            inverseJoinColumns = @JoinColumn(name = "skills_skill_name"))
    private Set<Skill> skills;

    //MANQUAIT AU MAIN (A AJOUTER SUR LE MCD AUSSI!!)
    @ManyToMany(mappedBy = "teams")
    @JsonIgnore
    private Set<Manager> managers = new HashSet<>(); // HASHSET to avoid null pointer exception
}
