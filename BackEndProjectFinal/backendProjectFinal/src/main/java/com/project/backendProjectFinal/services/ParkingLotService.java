package com.project.backendProjectFinal.services;


import com.project.backendProjectFinal.dtos.ParkingLotsDTO;
import com.project.backendProjectFinal.exceptions.DataNotFoundException;
import com.project.backendProjectFinal.models.ParkingLots;
import com.project.backendProjectFinal.repositories.ParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParkingLotService implements IParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    @Override
    public ParkingLots createParkingLot(ParkingLotsDTO parkingLotsDTO) throws Exception {
        String parkingId = parkingLotsDTO.getId();

        // kiem tra id da ton tai chua
        if (parkingLotRepository.existsById(parkingId)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        ParkingLots newParkingLot = ParkingLots.builder()
                .id(parkingLotsDTO.getId())
                .fullName(parkingLotsDTO.getFullName())
                .location(parkingLotsDTO.getLocation())
                .capacity(parkingLotsDTO.getCapacity())
                .status(parkingLotsDTO.getStatus())
                .build();

        return parkingLotRepository.save(newParkingLot);
    }

    @Override
    public ParkingLots getParkingById(String parkingId) throws Exception {
        return parkingLotRepository.findById(parkingId)
                .orElseThrow(()->new DataNotFoundException("Cannot find parking with ID: "+parkingId)
                        );
    }

    @Override
    public ParkingLots updateParkingLot(String id, ParkingLotsDTO parkingLotsDTO) throws Exception {
        ParkingLots existingParkingLot =getParkingById(id);
        if (existingParkingLot!= null) {
            existingParkingLot.setId(parkingLotsDTO.getId());
            existingParkingLot.setFullName(parkingLotsDTO.getFullName());
            existingParkingLot.setCapacity(parkingLotsDTO.getCapacity());
            existingParkingLot.setLocation(parkingLotsDTO.getLocation());
            existingParkingLot.setStatus(parkingLotsDTO.getStatus());
            return parkingLotRepository.save(existingParkingLot);
        }
        return null;
    }
}
