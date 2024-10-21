package com.PiXl.mainframe.dto;

import com.PiXl.mainframe.entities.UserEntity;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String message;
    private boolean isAuthenticated;
    private Optional<UserEntity> user;
}
