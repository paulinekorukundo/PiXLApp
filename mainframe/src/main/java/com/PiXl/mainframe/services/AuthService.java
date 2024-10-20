package com.PiXl.mainframe.services;

import java.util.Optional;

import com.PiXl.mainframe.Models.Users;

public interface AuthService {

    /**
     * Login user with email and password
     * 
     * @param email
     * @param password
     * @return boolean - true if login is successful else false
     */
    boolean login(String email, String password);

    /**
     * Register user with username, email and password
     * 
     * @param username
     * @param email
     * @param password
     * @return Optional<Users> - User object if registration is successful else
     *         empty
     */
    Optional<Users> register(String username, String email, String password);
}
