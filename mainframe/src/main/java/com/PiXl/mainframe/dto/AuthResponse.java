package com.PiXl.mainframe.dto;

import java.util.Optional;

import com.PiXl.mainframe.entities.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private boolean isAuthenticated;
    private Optional<UserEntity> user;
}