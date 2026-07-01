package com.api.pratica.domain.post;

import jakarta.validation.constraints.NotNull;

public record DataUpdatePost(
        @NotNull
        Long id,
        String title,
        String content
) { }
