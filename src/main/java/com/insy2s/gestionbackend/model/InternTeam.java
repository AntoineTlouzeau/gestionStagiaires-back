package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.model.embedded.InternTeamId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intern_team")
public class InternTeam {
    @EmbeddedId
    private InternTeamId id;

    @ManyToOne
    @JoinColumn(name = "Id_intern", insertable=false, updatable=false)
    private Intern intern;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "Id_team", insertable=false, updatable=false)
    private Team team;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;



}
