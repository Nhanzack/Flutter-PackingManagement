package com.project.backendProjectFinal.repositories;

import com.project.backendProjectFinal.models.Users;
import com.project.backendProjectFinal.models.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicles, String> {
    boolean existsById(String id);
    List<Vehicles> findByUser(Users user);
}
