package com.api.pratica.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String hello() {
        return "hello";
    }
}
