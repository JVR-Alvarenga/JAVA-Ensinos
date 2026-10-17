package com.reserva.salas.domain.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DataRegisterEmployee(
        @NotBlank(message = "The Name is required")
        String name,
        @NotBlank(message = "The Position is required")
        String position,
        @NotBlank @Email(message = "The Email is required")
        String email,
        @NotBlank(message = "The Password is required") @Size(min = 6, message = "The Password min 6 caracther")
        String password
) { }
