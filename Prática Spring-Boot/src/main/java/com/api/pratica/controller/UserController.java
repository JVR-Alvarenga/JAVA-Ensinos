package com.api.pratica.controller;

import com.api.pratica.domain.user.DataDatailUser;
import com.api.pratica.domain.user.DataRegisterUser;
import com.api.pratica.domain.user.DataUpdateUser;
import com.api.pratica.domain.user.User;
import com.api.pratica.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Rotas -------------------------------------------------------------------------------
    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid DataRegisterUser data, UriComponentsBuilder uriBuilder) {
        String hashPassword = passwordEncoder.encode(data.password());
        User user = new User(data, hashPassword);
        repository.save(user);

        var uri = uriBuilder.path("/users/detail/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDatailUser(user));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DataDatailUser>> listUsers(@PageableDefault(sort = "id") Pageable pageable) {
        var list = repository.findByActiveTrue(pageable).map(DataDatailUser::new);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DataDatailUser> detailUser(@PathVariable Long id) {
        User user = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDatailUser(user));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DataDatailUser> update(@RequestBody @Valid DataUpdateUser data) {
        User user = repository.getReferenceById(data.id());

        user.updateData(data);
        return ResponseEntity.ok(new DataDatailUser(user));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        User user = repository.getReferenceById(id);

        user.delete();
        return ResponseEntity.noContent().build();
    }

}
