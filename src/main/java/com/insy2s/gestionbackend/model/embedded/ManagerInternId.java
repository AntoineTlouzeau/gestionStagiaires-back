package com.insy2s.gestionbackend.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ManagerInternId implements Serializable {
    @Column(name = "Id_manager")
    private Long managerId;

    @Column(name = "Id_intern")
    private Long internId;
}
