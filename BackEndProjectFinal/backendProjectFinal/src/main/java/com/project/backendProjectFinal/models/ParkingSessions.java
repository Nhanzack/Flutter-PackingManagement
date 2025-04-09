package com.project.backendProjectFinal.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "parking_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ParkingSessions extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_sessionID")
    private Long id;


    @Column(name = "pic_entry")
    private String picEntry;

    @Column(name = "pic_exit")
    private String picExit;

    @Column(name = "fee")
    private int fee;

    @Column(name = "total")
    private Long total;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingLots parkingLots;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicles vehicles;

}
