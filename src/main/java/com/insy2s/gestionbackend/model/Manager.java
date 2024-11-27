package com.insy2s.gestionbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_manager", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "is_validated", nullable = false)
    private Boolean isValidated;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_name")
    private Role roleName;

    @ManyToMany(mappedBy = "managers")
    private Set<Meeting> meetings;

    @ManyToMany
    @JoinTable(
            name = "team_managers",
            joinColumns = @JoinColumn(name = "id_manager"),
            inverseJoinColumns = @JoinColumn(name = "id_team")
    )
    private Set<Team> teams;
}