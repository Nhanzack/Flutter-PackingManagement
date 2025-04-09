package com.project.backendProjectFinal.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "parking_lots")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ParkingLots {
    @Id
    @Column(name = "parking_id")
    @NotBlank(message = "ID cannot be blank")
    private String id;

    @Column(name = "fullname", length = 100)
    private String fullName;

    @Column(name = "location")
    private String location;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "status")
    private String status;


}
