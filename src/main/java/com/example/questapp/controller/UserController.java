package com.example.questapp.controller;

import com.example.questapp.helper.GenericResponse;
import com.example.questapp.model.User;
import com.example.questapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser) {
        return ResponseEntity.ok(userService.save(newUser));
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getOneUser(@PathVariable String username) {
        //Todo: custom exception
        return ResponseEntity.ok(userService.getOneUser(username).orElse(null));
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateOneUser(@PathVariable String username, @RequestBody User newUser) {
        return ResponseEntity.ok(userService.updateOneUser(username, newUser));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteOneUser(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.ok(new GenericResponse(username + " is deleted!"));
    }

}
