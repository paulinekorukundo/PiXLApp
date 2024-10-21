package com.PiXl.mainframe.entities;

import com.PiXl.mainframe.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity()
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    private String user_id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String profile_picture;
    private String bio;
    @JsonIgnore
    private Boolean logged_in_status;

    public UserEntity(User user) {
        this(user.getUser_id(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getProfile_picture(), user.getBio(), user.getLogged_in_status());
    }
}