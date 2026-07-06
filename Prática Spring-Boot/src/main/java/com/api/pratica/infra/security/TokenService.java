package com.api.pratica.infra.security;

import com.api.pratica.domain.user.User;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.auth0.jwt.JWT;

import static com.auth0.jwt.JWT.require;


@Service
public class TokenService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Alvarenga API")
                    .withSubject(user.getEmail())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(algorithm);
        } catch ( JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Alvarenga API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado", exception);
        }
    }
}
