package com.api.pratica.domain.post;

import com.api.pratica.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne @JoinColumn(name = "user_id")
    private User user;


    public Post(DataRegisterPost data, User user) {
        this.title = data.title();
        this.content = data.content();
        this.user = user;
    }
}
