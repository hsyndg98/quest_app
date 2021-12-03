package com.example.questapp.service;

import com.example.questapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User newUser);

    Optional<User> getOneUser(String username);

    void delete(String username);

    User updateOneUser(String username, User newUser);
}
