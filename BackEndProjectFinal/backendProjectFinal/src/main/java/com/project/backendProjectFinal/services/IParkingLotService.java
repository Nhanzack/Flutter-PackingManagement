package com.project.backendProjectFinal.services;

import com.project.backendProjectFinal.dtos.ParkingLotsDTO;
import com.project.backendProjectFinal.models.ParkingLots;

public interface IParkingLotService {
    ParkingLots createParkingLot(ParkingLotsDTO parkingLotsDTO) throws Exception;
    ParkingLots getParkingById(String id) throws Exception;
    ParkingLots updateParkingLot(String id, ParkingLotsDTO parkingLotsDTO) throws Exception;
}
