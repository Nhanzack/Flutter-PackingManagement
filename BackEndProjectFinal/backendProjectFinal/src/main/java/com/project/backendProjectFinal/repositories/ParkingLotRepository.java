package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.ParkingLots;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLots, String> {
    boolean existsById(String id);
}
