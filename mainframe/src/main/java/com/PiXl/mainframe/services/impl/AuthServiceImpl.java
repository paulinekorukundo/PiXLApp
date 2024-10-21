package com.PiXl.mainframe.services.impl;

import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.Models.User;
import com.PiXl.mainframe.dto.AuthResponse;
import com.PiXl.mainframe.entities.UserEntity;
import com.PiXl.mainframe.entities.UserEntityId;
import com.PiXl.mainframe.repositories.UsersRepository;
import com.PiXl.mainframe.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public AuthResponse login(String email, String password) {
        // verify if user exists
        Optional<UserEntity> user = usersRepository.findByEmail(email);
        AuthResponse data = new AuthResponse();
        data.setAuthenticated(false);
        data.setUser(Optional.empty());

        if (!user.isPresent()) {
            data.setMessage("User not found");
            return data;
        } else if (user.get().getPassword().equals(password)) {
            data.setAuthenticated(true);
            data.setMessage("Login successful");
            data.setUser(user);
            return data;
        }
        data.setMessage("Email or Password is incorrect");
        return data;
    }

    @Override
    public AuthResponse register(String username, String email, String password) {
        // verify if user exists
        Optional<UserEntity> user = usersRepository.findByEmail(email);
        AuthResponse data = new AuthResponse();
        data.setAuthenticated(false);
        data.setUser(Optional.empty());

        if (user.isPresent()) {
            data.setMessage("User already exists");
            return data;
        }

        User newUser = new User("u3", username, email, password, "", "", false);
        UserEntity newUserEntity = new UserEntity(new UserEntityId(newUser.getUserId()), newUser.getUsername(),
                newUser.getEmail(), newUser.getPassword(), newUser.getProfile_picture(), newUser.getBio(),
                newUser.getLogged_in_status());
        usersRepository.save(newUserEntity);
        data.setAuthenticated(true);
        data.setMessage("Registration successful");
        data.setUser(Optional.of(newUserEntity));
        return data;
    }
}
