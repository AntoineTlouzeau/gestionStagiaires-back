package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.customenum.Presence;
import com.insy2s.gestionbackend.model.embedded.InternSkillId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intern_meeting")
public class InternMeeting {
    @EmbeddedId
    private InternSkillId id;

    @ManyToOne
    @JoinColumn(name = "Id_intern", insertable=false, updatable=false)
    private Intern intern;

    @ManyToOne
    @JoinColumn(name = "skill_name", insertable=false, updatable=false)
    private Skill skill;

    @Column(name = "presence")
    private Presence presence;

    @Column(name = "comment")
    private String comment;
}
