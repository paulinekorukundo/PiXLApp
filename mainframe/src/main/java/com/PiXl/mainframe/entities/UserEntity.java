package com.PiXl.mainframe.entities;

import com.PiXl.mainframe.models.User;
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
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private Boolean logged_in_status;

    public UserEntity(User user) {
        this(user.getEmail(), user.getPassword(), user.getLogged_in_status());
    }
}