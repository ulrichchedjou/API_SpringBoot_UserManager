package com.KFOKAM48.Users_Management.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record JwtRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {}
