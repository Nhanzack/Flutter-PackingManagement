package com.project.backendProjectFinal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Vehicles {
    @Id
    @Column(name = "vehicle_id")
    @NotBlank(message = "ID cannot be blank")
    private String id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
