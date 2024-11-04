package com.PiXl.mainframe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.PiXl.mainframe.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    // Optional<User> findByUsername(String username);

    // Optional<User> findByEmailAndPassword(String email, String password);

    // Optional<User> findByUsernameAndPassword(String username, String password);

}
