package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.model.embedded.ManagerInternId;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager_intern")
public class ManagerIntern {
    @EmbeddedId
    private ManagerInternId id;

    @ManyToOne
    @JoinColumn(name = "Id_manager", insertable=false, updatable=false)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "Id_intern", insertable=false, updatable=false)
    private Intern intern;

    @Column(name = "note")
    private Float note;

    @Column(name = "eval_date")
    private Date evalDate;

    @Column(name = "comment")
    private String comment;
}
