package com.api.pratica.domain.user;

public record DataDatailUser(
        Long id,
        String name,
        String telephone,
        String email
) {
    public DataDatailUser(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getTelephone(),
            user.getEmail()
        );
    }
}
