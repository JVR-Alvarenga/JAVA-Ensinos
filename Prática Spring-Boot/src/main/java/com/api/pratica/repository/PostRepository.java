package com.api.pratica.repository;

import com.api.pratica.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository   extends JpaRepository<Post, Long> { }
