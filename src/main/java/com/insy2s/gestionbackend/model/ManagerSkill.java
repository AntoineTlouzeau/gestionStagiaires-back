package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.model.embedded.ManagerSkillId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager_skill")
public class ManagerSkill {
    @EmbeddedId
    private ManagerSkillId id;

    @ManyToOne
    @JoinColumn(name = "Id_manager", insertable=false, updatable=false)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "skill_name", insertable=false, updatable=false)
    private Skill skill;

    @Column(name = "level")
    private Integer level;
}