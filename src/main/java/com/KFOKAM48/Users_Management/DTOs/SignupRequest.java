package com.KFOKAM48.Users_Management.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank @Size(min = 2, max = 50) String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password
) {}
