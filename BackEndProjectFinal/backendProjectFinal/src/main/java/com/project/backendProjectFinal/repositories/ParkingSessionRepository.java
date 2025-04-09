package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.ParkingSessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSessions, Long> {
    @Query("SELECT ps FROM ParkingSessions ps WHERE ps.vehicles.user.id = :userId AND ps.status = 'ON'")
    Optional<ParkingSessions> findActiveSessionByUserId(@Param("userId") Long userId);

    // Lấy danh sách ParkingSessions theo userId
    @Query("SELECT ps FROM ParkingSessions ps WHERE ps.vehicles.user.id = :userId")
    List<ParkingSessions> findByUserId(@Param("userId") Long userId);
}
