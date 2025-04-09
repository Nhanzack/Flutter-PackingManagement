package com.project.backendProjectFinal.services;


import com.project.backendProjectFinal.dtos.ParkingSessionsDTO;
import com.project.backendProjectFinal.dtos.ParkingSessionsVisitorDTO;
import com.project.backendProjectFinal.exceptions.DataNotFoundException;
import com.project.backendProjectFinal.models.ParkingLots;
import com.project.backendProjectFinal.models.ParkingSessions;
import com.project.backendProjectFinal.models.ParkingSessionsVisitor;
import com.project.backendProjectFinal.models.Vehicles;
import com.project.backendProjectFinal.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ParkingSessionService {
    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingSessionVisitorRepository parkingSessionVisitorRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final UserRepository userRepository;

    public void createParkingSession(Long userId, ParkingSessionsDTO dto) {
        Optional<Vehicles> vehicleOpt = vehicleRepository.findById(dto.getVehicleId());
        Optional<ParkingLots> parkingLotOpt = parkingLotRepository.findById(dto.getParkingId());

        try {
            ParkingLots parkingLot = parkingLotRepository.findById(dto.getParkingId())
                    .orElseThrow(() -> new DataNotFoundException("Cannot find parking lot with ID: " + dto.getParkingId()));
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (vehicleOpt.isPresent()) {
            ParkingSessions session = new ParkingSessions();
            session.setPicEntry(dto.getPicEntry());
            session.setFee(000); // Fee mặc định
            session.setVehicles(vehicleOpt.get());
            session.setParkingLots(parkingLotOpt.get());
            parkingSessionRepository.save(session);
        } else {
            ParkingSessionsVisitor visitorSession = new ParkingSessionsVisitor();
            visitorSession.setVehicleVisitor(dto.getVehicleId());
            visitorSession.setPicEntry(dto.getPicEntry());
            visitorSession.setFee(000); // Fee mặc định
            visitorSession.setParkingLots(parkingLotOpt.get());
            parkingSessionVisitorRepository.save(visitorSession);
        }
    }

    public void checkoutSession(Long userId, String picExit) {
        Optional<ParkingSessions> activeSessionOpt = parkingSessionRepository.findActiveSessionByUserId(userId);

        if (activeSessionOpt.isPresent()) {
            ParkingSessions session = activeSessionOpt.get();
            session.setPicExit(picExit);
            session.setTotal(calculateTotal(session.getFee(), session.getEntryTime(), session.getExitTime()));
            parkingSessionRepository.save(session);
        } else {
            Optional<ParkingSessionsVisitor> activeVisitorSessionOpt = parkingSessionVisitorRepository.findActiveVisitorSessionByUserId(userId);

            if (activeVisitorSessionOpt.isPresent()) {
                ParkingSessionsVisitor visitorSession = activeVisitorSessionOpt.get();
                visitorSession.setPicExit(picExit);
                visitorSession.setTotal(calculateTotal(visitorSession.getFee(), visitorSession.getEntryTime(), visitorSession.getExitTime()));
                parkingSessionVisitorRepository.save(visitorSession);
            }
        }
    }

    private Long calculateTotal(int fee, LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Duration.between(entryTime, exitTime).toHours();
        return (hours <= 3) ? fee : fee * ((hours / 3) + 1);
    }

    // Lấy danh sách tất cả parking sessions
    public List<ParkingSessions> getAllParkingSessions() {
        return parkingSessionRepository.findAll();
    }

    // Lấy danh sách tất cả parking sessions visitor
    public List<ParkingSessionsVisitor> getAllParkingSessionVisitors() {
        return parkingSessionVisitorRepository.findAll();
    }

    // Lấy danh sách parking sessions theo userId
    public List<ParkingSessions> getParkingSessionsByUserId(Long userId) {
        return parkingSessionRepository.findByUserId(userId);
    }
}

