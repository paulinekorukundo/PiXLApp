package com.PiXl.mainframe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
