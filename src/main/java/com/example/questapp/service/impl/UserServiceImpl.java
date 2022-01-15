package com.example.questapp.service.impl;

import com.example.questapp.model.User;
import com.example.questapp.repository.UserRepository;
import com.example.questapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> getOneUser(String username) {
        //Todo: custom exception
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateOneUser(@PathVariable String username, @RequestBody User newUser) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() &&
                userRepository.findByUsername(user.get().getUsername()).isEmpty()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }
    }

    @Override
    public void delete(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User foundUser = user.get();
            userRepository.delete(foundUser);
            log.info(user.get().getUsername() + " is deleted.");
        } else {
            //ToDo: custom exception
        }
    }

}
