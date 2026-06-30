package com.api.pratica.domain.user;

import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,
        String name,
        String telephone,
        String email,
        String password
) { }
