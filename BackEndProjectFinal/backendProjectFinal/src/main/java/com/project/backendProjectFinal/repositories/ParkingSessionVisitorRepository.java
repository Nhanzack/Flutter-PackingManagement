package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.ParkingSessionsVisitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParkingSessionVisitorRepository extends JpaRepository<ParkingSessionsVisitor, Long> {
    @Query("SELECT psv FROM ParkingSessionsVisitor psv WHERE psv.vehicleVisitor = :userId AND psv.status = 'ON'")
    Optional<ParkingSessionsVisitor> findActiveVisitorSessionByUserId(@Param("userId") Long userId);
}
