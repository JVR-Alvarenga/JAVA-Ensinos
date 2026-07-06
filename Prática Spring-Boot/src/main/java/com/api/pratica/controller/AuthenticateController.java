package com.api.pratica.controller;

import com.api.pratica.domain.user.DataAuthenticate;
import com.api.pratica.domain.user.DataTokenJWT;
import com.api.pratica.domain.user.User;
import com.api.pratica.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticateController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    public AuthenticateController(AuthenticationManager manager, TokenService tokenService) {
        this.manager = manager;
        this.tokenService = tokenService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity authenticate(@RequestBody @Valid DataAuthenticate data) {
       var authenticateToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
       var authenticate = manager.authenticate(authenticateToken);

       var tokenJWT = tokenService.generateToken((User) authenticate.getPrincipal());

       return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }

}
