package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.model.embedded.InternSkillId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intern_skill")
public class InternSkill {
    @EmbeddedId
    private InternSkillId id;

    @ManyToOne
    @JoinColumn(name = "Id_intern", insertable=false, updatable=false)
    private Intern intern;

    @ManyToOne
    @MapsId("skillName")
    @JoinColumn(name = "skill_name", insertable=false, updatable=false)
    private Skill skill;

    @Column(name = "level")
    private Integer level;
}
