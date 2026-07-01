package com.api.pratica.domain.post;

import com.api.pratica.domain.user.User;

public record DataDetailPost(
        Long id,
        String title,
        String content,
        User user
) {
    public DataDetailPost(Post post) {
        this(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getUser()
        );
    }
}
