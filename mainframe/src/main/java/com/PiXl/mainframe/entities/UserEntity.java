package com.PiXl.mainframe.entities;

import com.PiXl.mainframe.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
    @EmbeddedId
    private UserEntityId id;

    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String profile_picture;
    private String bio;
    @JsonIgnore
    private Boolean logged_in_status;

    public UserEntityId getUserId() {
        return id;
    }

    public UserEntity(User user) {
        this(new UserEntityId(user.getUserId()), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getProfile_picture(), user.getBio(), user.getLogged_in_status());
    }
}