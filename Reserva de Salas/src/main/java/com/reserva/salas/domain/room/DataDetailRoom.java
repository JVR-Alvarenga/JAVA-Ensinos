package com.reserva.salas.domain.room;

public record DataDetailRoom(
        Long id,
        Boolean isBusy,
        String name
) {
    public DataDetailRoom(Room data) {
        this(
            data.getId(),
            data.getIsBusy(),
            data.getName()
        );
    }
}
