package com.reserva.salas.domain.employee;

public record DataListEmployee(
        Long id,
        String name,
        String email,
        String position
) {
    public DataListEmployee(Employee employee) {
        this(
            employee.getId(),
            employee.getName(),
            employee.getEmail(),
            employee.getPosition()
        );
    }
}
