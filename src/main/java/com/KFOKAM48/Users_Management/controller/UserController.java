package com.KFOKAM48.Users_Management.controller;

import com.KFOKAM48.Users_Management.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.KFOKAM48.Users_Management.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Get all users")
    public List<UserModel> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}