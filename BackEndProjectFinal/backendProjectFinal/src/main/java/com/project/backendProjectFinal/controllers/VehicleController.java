package com.project.backendProjectFinal.controllers;

import com.project.backendProjectFinal.dtos.VehiclesDTO;
import com.project.backendProjectFinal.models.Vehicles;
import com.project.backendProjectFinal.services.IVehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/vehicle")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {
    private final IVehicleService vehicleService;

    // Create a new vehicle
    @PostMapping("")
    public ResponseEntity<?> createVehicle(@Valid @RequestBody VehiclesDTO vehiclesDTO, BindingResult result) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Vehicles newVehicle = vehicleService.createVehicle(vehiclesDTO);
            return ResponseEntity.ok(newVehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable String id) {
        try {
            Vehicles vehicle = vehicleService.getVehicleById(id);
            return ResponseEntity.ok(vehicle);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleById(@PathVariable String id) {
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    // Get all vehicles of a user by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicles>> findVehiclesByUserId(@PathVariable Long userId) {
        List<Vehicles> vehicles = vehicleService.findByUserId(userId);
        return ResponseEntity.ok(vehicles);
    }
}
