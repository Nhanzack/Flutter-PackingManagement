package com.project.backendProjectFinal.services;

import java.util.Objects;

public interface IParkingSessionService {
    Object createParkingSession(String existsById, String picEntry, String parkingId) throws Exception;
    void checkoutSession(Long sessionId, boolean isVisitor, String picEntry) throws Exception;
}
