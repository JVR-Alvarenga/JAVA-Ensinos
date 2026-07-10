package com.api.pratica.domain.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DataRegisterPost(
    @NotBlank(message = "Title is required")
    String title,
    @NotBlank(message = "Content is required")
    String content
) { }
