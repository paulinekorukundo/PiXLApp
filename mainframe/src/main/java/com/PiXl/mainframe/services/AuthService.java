package com.PiXl.mainframe.services;

import java.util.Optional;

import com.PiXl.mainframe.Models.User;
import com.PiXl.mainframe.dto.AuthResponse;

public interface AuthService {

    /**
     * Login user with email and password
     * 
     * @param email
     * @param password
     * @return AuthResponse - User object if login is successful else empty
     */
    AuthResponse login(String email, String password);

    /**
     * Register user with username, email and password
     * 
     * @param username
     * @param email
     * @param password
     * @return AuthResponse - User object if registration is successful else empty
     */
    AuthResponse register(String username, String email, String password);
}
