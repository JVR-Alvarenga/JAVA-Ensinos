package com.reserva.salas.domain.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterReservation(
        @NotNull(message = "The Employee is required")
        Long employeeId,
        @NotNull(message = "The Room is required")
        Long roomId,
        @NotNull(message = "The Start Time is required") @Future(message = "The Start Time must be in the future")
        LocalDateTime startTime,
        @NotNull(message = "The End Time is required") @Future(message = "The End Time must be in the future")
        LocalDateTime endTime
) { }
