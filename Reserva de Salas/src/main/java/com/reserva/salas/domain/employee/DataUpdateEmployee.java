package com.reserva.salas.domain.employee;

import jakarta.validation.constraints.NotNull;

public record DataUpdateEmployee(
        @NotNull
        Long id,
        String name,
        String email,
        String position,
        String password
) { }
