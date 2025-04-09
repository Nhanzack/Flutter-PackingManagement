package com.project.backendProjectFinal.controllers;

import com.project.backendProjectFinal.dtos.ParkingSessionsDTO;
import com.project.backendProjectFinal.services.ParkingSessionService;
import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/parking_session")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingSessionController {
    private final ParkingSessionService parkingSessionService;

    // Tạo Parking Session
    @PostMapping("/checkin")
    public ResponseEntity<?> createSession(
            @RequestParam Long userId,
            @Valid @RequestBody ParkingSessionsDTO dto,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            parkingSessionService.createParkingSession(userId, dto);
            return ResponseEntity.ok("Parking session created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Checkout Parking Session
    @PutMapping("/checkout")
    public ResponseEntity<?> checkoutSession(
            @RequestParam Long userId,
            @RequestParam String picExit
    ) {
        try {
            parkingSessionService.checkoutSession(userId, picExit);
            return ResponseEntity.ok("Checkout successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/sessions")
    public ResponseEntity<?> getAllParkingSessions() {
        return ResponseEntity.ok(parkingSessionService.getAllParkingSessions());
    }

    @GetMapping("/visitor_sessions")
    public ResponseEntity<?> getAllParkingVisitorSessions() {
        return ResponseEntity.ok(parkingSessionService.getAllParkingSessionVisitors());
    }

    @GetMapping("/sessions/user/{userId}")
    public ResponseEntity<?> getParkingSessionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(parkingSessionService.getParkingSessionsByUserId(userId));
    }
}
