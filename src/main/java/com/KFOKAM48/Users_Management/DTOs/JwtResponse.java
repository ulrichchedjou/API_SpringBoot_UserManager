package com.KFOKAM48.Users_Management.DTOs;

public record JwtResponse(
        String token,
        UserResponse user
) {}
