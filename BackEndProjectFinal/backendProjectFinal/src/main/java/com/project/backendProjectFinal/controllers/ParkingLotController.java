package com.project.backendProjectFinal.controllers;

import com.project.backendProjectFinal.dtos.ParkingLotsDTO;
import com.project.backendProjectFinal.models.ParkingLots;
import com.project.backendProjectFinal.services.IParkingLotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/parking_lot")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingLotController {
    private final IParkingLotService parkingLotService;
    @PostMapping("")
    //POST http://localhost:8088/v1/api/parking_lot
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ParkingLotsDTO parkingLotsDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ParkingLots newParkingLot = parkingLotService.createParkingLot(parkingLotsDTO);
            return ResponseEntity.ok(newParkingLot);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //update parking
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParking(@PathVariable String id, @Valid @RequestBody ParkingLotsDTO parkingLotsDTO) {
        try {
            ParkingLots updateParking = parkingLotService.updateParkingLot(id, parkingLotsDTO);
            return ResponseEntity.ok(updateParking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
