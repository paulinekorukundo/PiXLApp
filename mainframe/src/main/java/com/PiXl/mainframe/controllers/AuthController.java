package com.PiXl.mainframe.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

import com.PiXl.mainframe.Models.Users;
import com.PiXl.mainframe.services.AuthService;

@RestController()
@RequestMapping("/api/v1/auth")
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Login user with email and password
     * 
     * @param email
     * @param password
     * 
     * @return boolean - true if login is successful else false
     */
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody String email, @RequestBody String password) {
        System.out.println("email: " + email + " password: " + password);
        // authService.login(email, password);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/register")
    public ResponseEntity<Optional<Users>> register(@RequestBody String username, @RequestBody String email,
            @RequestBody String password) {
        return ResponseEntity.ok(authService.register("username", "email", "password"));
    }
}
