package com.project.backendProjectFinal.services;

import com.project.backendProjectFinal.dtos.VehiclesDTO;
import com.project.backendProjectFinal.exceptions.DataNotFoundException;
import com.project.backendProjectFinal.models.Users;
import com.project.backendProjectFinal.models.Vehicles;
import com.project.backendProjectFinal.repositories.UserRepository;
import com.project.backendProjectFinal.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleService implements IVehicleService {
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicles createVehicle(VehiclesDTO vehiclesDTO) throws Exception {
        Users existingUser = userRepository
                .findById(vehiclesDTO.getUsersId())
                .orElseThrow(()->new DataNotFoundException("Cannot find user with Id: "+vehiclesDTO.getUsersId())
                        );
        Vehicles newVehicle = Vehicles.builder()
                .id(vehiclesDTO.getId())
                .user(existingUser)
                .type(vehiclesDTO.getType())
                .build();
        return vehicleRepository.save(newVehicle);
    }

    @Override
    public Vehicles getVehicleById(String id) throws Exception {
        return vehicleRepository.findById(id)
                .orElseThrow(()-> new DataNotFoundException("Cannot find vehicle with id: "+id)
                        );
    }

    @Override
    public void deleteVehicleById(String id) {
        Optional<Vehicles> optionalVehicles =vehicleRepository.findById(id);
        optionalVehicles.ifPresent(vehicleRepository::delete);
    }

    @Override
    public List<Vehicles> findByUserId(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return vehicleRepository.findByUser(user);
    }
}
