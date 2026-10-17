package com.reserva.salas.domain.employee;

public record DataDetailEmployee(
        Long id,
        String name,
        String email,
        String position
) {
    public DataDetailEmployee(Employee data) {
        this(
            data.getId(),
            data.getName(),
            data.getEmail(),
            data.getPosition()
        );
    }
}
