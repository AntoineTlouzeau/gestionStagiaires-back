package com.insy2s.gestionbackend.model;

import com.insy2s.gestionbackend.customenum.MeetingType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_meeting", nullable = false)
    private Long id;

    @Column(name = "meeting_date", nullable = false)
    private Date meetingDate;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MeetingType type;

    @OneToOne
    @JoinColumn(name = "Id_team")
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "meeting_manager",
            joinColumns = @JoinColumn(name = "Id_meeting"),
            inverseJoinColumns = @JoinColumn(name = "Id_manager"))

    private Set<Manager> managers;
}