package com.reserva.salas.domain.room;

import jakarta.validation.constraints.NotNull;

public record DataUpdateRoom(
        @NotNull
        Long id,
        Boolean isBusy,
        String name
) { }
