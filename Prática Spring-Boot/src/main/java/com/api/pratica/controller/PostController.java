package com.api.pratica.controller;

import com.api.pratica.domain.post.DataDetailPost;
import com.api.pratica.domain.post.DataRegisterPost;
import com.api.pratica.domain.post.Post;
import com.api.pratica.domain.post.DataUpdatePost;
import com.api.pratica.domain.user.User;
import com.api.pratica.repository.PostRepository;
import com.api.pratica.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/posts")
public class PostController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public PostController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<DataDetailPost> addPost(@RequestBody @Valid DataRegisterPost data, UriComponentsBuilder uriBuilder) {
        User user = userRepository.getReferenceById(data.userId());
        Post post = new Post(data, user);
        postRepository.save(post);

        var uri = uriBuilder.path("/posts/detail/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailPost(post));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DataDetailPost>> listPosts(@PageableDefault(sort = "id") Pageable pageable) {
        var list = postRepository.findAll(pageable).map(DataDetailPost::new);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DataDetailPost> getPost(@PathVariable("id") Long id) {
        Post post = postRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailPost(post));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DataDetailPost> updatePost(@RequestBody @Valid DataUpdatePost data) {
        Post post = postRepository.getReferenceById(data.id());
        post.updateData(data);

        return ResponseEntity.ok(new DataDetailPost(post));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
