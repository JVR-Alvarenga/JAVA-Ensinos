package com.reserva.salas.domain.room;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterRoom(
        @NotBlank(message = "Name is required")
        String name
) { }
