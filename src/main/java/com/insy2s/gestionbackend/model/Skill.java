package com.insy2s.gestionbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    private Set<Team> teams;
}
