package com.KFOKAM48.Users_Management.repository;

import com.KFOKAM48.Users_Management.DTOs.SignupRequest;
import com.KFOKAM48.Users_Management.DTOs.UserResponse;
import com.KFOKAM48.Users_Management.model.UserModel;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "message", ignore = true)
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    UserResponse toUserResponse(UserModel user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", constant = "USER")
    default UserModel toUser(SignupRequest request, PasswordEncoder encoder) {
        return UserModel.builder()
                .name(request.name())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role("ROLE_USER")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "signupRequest.name", target = "name")
    @Mapping(source = "signupRequest.email", target = "email")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserModel signupRequestToUser(SignupRequest signupRequest);

    @Mapping(target = "password", expression = "java(encodePassword(signupRequest, passwordEncoder))")
    @Mapping(source = "signupRequest.name", target = "name")
    @Mapping(source = "signupRequest.email", target = "email")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserModel signupRequestToUserWithPassword(SignupRequest signupRequest, PasswordEncoder passwordEncoder);

    default String encodePassword(SignupRequest source, PasswordEncoder encoder) {
        return encoder.encode(source.password());
    }

    @Mapping(target = "message", ignore = true)
    UserResponse toUserResponse(User user);

    default UserModel toUserWithEncodedPassword(SignupRequest request, PasswordEncoder encoder) {
        return UserModel.builder()
                .name(request.name())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role("ROLE_USER")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
