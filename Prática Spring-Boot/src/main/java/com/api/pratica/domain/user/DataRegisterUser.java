package com.api.pratica.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DataRegisterUser(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Telephone is required")
        @Size(min = 11, max = 11, message = "Telephone must have 11 characters")
        String telephone,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,

        @NotBlank(message = "Password is required")
        String password
) { }
