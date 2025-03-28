package com.KFOKAM48.Users_Management.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        String createdAt,
        @JsonInclude(JsonInclude.Include.NON_NULL) String message
) {
    // Constructeur pour les cas de succès
    public UserResponse(
            Long id, String name, String email, String role, String createdAt) {
        this(id, name, email, role, createdAt, null);
    }

    // Constructeur pour les erreurs
    public UserResponse(String message) {
        this(null, null, null, null, null, message);
    }
}