package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.customenum.PresenceType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "intern")
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_intern", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    @Column(name = "presence_type", nullable = false)
    private PresenceType presenceType;

    @Column(name = "trainings")
    private String trainings;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "url_cv")
    private String urlCv;

    @Column(name = "hired_by")
    private String hiredBy;

    @Column(name = "hired_at")
    private Date hiredAt;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "intern")
    private List<InternSkill> internSkills;

    @OneToMany(mappedBy = "intern")
    private List<InternTeam> internTeams;
}