package com.reserva.salas.domain.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataUpdateReservation(
        @NotNull
        Long id,
        Long roomId,
        @Future(message = "The Start Time must be in the future")
        LocalDateTime startTime,
        @Future(message = "The End Time must be in the future")
        LocalDateTime endTime,
        Status status
) { }
