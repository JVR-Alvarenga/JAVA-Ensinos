package com.api.pratica.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DataAuthenticate(
        @NotBlank
        String email,
        @NotBlank
        String password
) { }
