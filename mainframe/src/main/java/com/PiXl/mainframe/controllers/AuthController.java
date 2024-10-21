package com.PiXl.mainframe.controllers;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

import com.PiXl.mainframe.Models.User;
import com.PiXl.mainframe.dto.LoginRequest;
import com.PiXl.mainframe.dto.AuthResponse;
import com.PiXl.mainframe.dto.RegisterRequest;
import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.services.AuthService;

@RestController()
@RequestMapping("/api/v1/auth")
@NoArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("data is this", HttpStatus.OK);
    }

    /**
     * Login user with email and password
     * 
     * @param loginRequest - email and password
     * 
     * @return ResponseEntity<Boolean,UserEntity> - true if login is successful and
     *         user details otherwise false and empty
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse loginResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginResponse.isAuthenticated()) {
            return ResponseHandler.generateResponse(loginResponse.getMessage(), HttpStatus.OK, loginResponse);
        } else {
            return ResponseHandler.generateResponse(loginResponse.getMessage(), HttpStatus.UNAUTHORIZED, loginResponse);
        }

    }

    /**
     * Register user with username, email and password
     * 
     * @param registerRequest - username, email and password
     * 
     * @return ResponseEntity<Optional<Users>> - user details if registration is
     *         successful otherwise empty
     */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest) {
        AuthResponse registerResponse = authService.register(registerRequest.getUsername(), registerRequest.getEmail(),
                registerRequest.getPassword());
        if (registerResponse.isAuthenticated()) {
            return ResponseHandler.generateResponse(registerResponse.getMessage(), HttpStatus.OK, registerResponse);
        } else {
            return ResponseHandler.generateResponse(registerResponse.getMessage(), HttpStatus.BAD_REQUEST,
                    registerResponse);
        }
    }
}
