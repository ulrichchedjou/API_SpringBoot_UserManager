package com.KFOKAM48.Users_Management.service;
import com.KFOKAM48.Users_Management.DTOs.SignupRequest;
import com.KFOKAM48.Users_Management.model.*;
import com.KFOKAM48.Users_Management.repository.UserMapper;
import com.KFOKAM48.Users_Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserModel save(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserModel update(Long id, UserModel userDetails) {
        UserModel user = userRepository.findById(id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        if (userDetails.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserModel save(SignupRequest signupRequest) {
        UserModel user = userMapper.signupRequestToUserWithPassword(
                signupRequest,
                passwordEncoder
        );
        return userRepository.save(user);
    }
    public UserModel registerUser(SignupRequest request) {
        return userMapper.toUser(request, passwordEncoder);
    }
}