package com.project.backendProjectFinal.services;

import com.project.backendProjectFinal.dtos.VehiclesDTO;
import com.project.backendProjectFinal.models.Vehicles;

import java.util.List;

public interface IVehicleService {
    Vehicles createVehicle(VehiclesDTO vehiclesDTO) throws Exception;
    Vehicles getVehicleById(String id) throws Exception;
    void deleteVehicleById(String id);
    List<Vehicles> findByUserId(Long userId);
}
