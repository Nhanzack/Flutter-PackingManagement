package com.project.backendProjectFinal.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "parking_sessions_visitor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ParkingSessionsVisitor extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_visitorID")
    private Long id;

    @Column(name = "vehicle_visitorID", nullable = false)
    private String vehicleVisitor;


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

}
