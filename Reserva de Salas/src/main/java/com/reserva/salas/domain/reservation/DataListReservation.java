package com.reserva.salas.domain.reservation;

import java.time.LocalDateTime;

public record DataListReservation(
        Long id,
        String employeeName,
        String roomName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Status status
) {
    public DataListReservation(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getEmployee().getName(),
            reservation.getRoom().getName(),
            reservation.getStartTime(),
            reservation.getEndTime(),
            reservation.getStatus()
        );
    }
}
