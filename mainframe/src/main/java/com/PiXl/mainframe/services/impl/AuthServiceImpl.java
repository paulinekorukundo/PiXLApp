package com.PiXl.mainframe.services.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.Models.Users;
import com.PiXl.mainframe.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean login(String email, String password) {
        return true;
    }

    @Override
    public Optional<Users> register(String username, String email, String password) {
        return Optional.empty();
    }
}
