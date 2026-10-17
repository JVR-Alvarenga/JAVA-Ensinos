package com.reserva.salas.domain.reservation;

import java.time.LocalDateTime;

public record DataDetailReservation(
        Long id,
        Long employeeId,
        String employeeName,
        Long roomId,
        String roomName,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Status status
) {
    public DataDetailReservation(Reservation data) {
        this(
            data.getId(),
            data.getEmployee().getId(),
            data.getEmployee().getName(),
            data.getRoom().getId(),
            data.getRoom().getName(),
            data.getStartTime(),
            data.getEndTime(),
            data.getStatus()
        );
    }
}
