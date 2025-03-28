
package com.KFOKAM48.Users_Management.controller;

import com.KFOKAM48.Users_Management.DTOs.JwtRequest;
import com.KFOKAM48.Users_Management.DTOs.JwtResponse;
import com.KFOKAM48.Users_Management.DTOs.SignupRequest;
import com.KFOKAM48.Users_Management.DTOs.UserResponse;
import com.KFOKAM48.Users_Management.model.UserModel;
import com.KFOKAM48.Users_Management.repository.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.KFOKAM48.Users_Management.service.UserService;
import com.KFOKAM48.Users_Management.utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "Login a user")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        UserModel user = (UserModel) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(user);

        return ResponseEntity.ok(
                new JwtResponse(jwt, userMapper.toUserResponse(user))
        );
    }

    @PostMapping("/signup")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody SignupRequest request) {
        if (userService.existsByEmail(request.email())) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        UserModel newUser = userMapper.toUserWithEncodedPassword(request, passwordEncoder);
        UserModel savedUser = userService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toUserResponse(savedUser));
    }
}