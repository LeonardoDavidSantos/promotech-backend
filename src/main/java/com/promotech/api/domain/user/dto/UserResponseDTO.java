package com.promotech.api.domain.user.dto;

import com.promotech.api.domain.user.UserRole;

import java.util.UUID;

public record UserResponseDTO(UUID id, String username, UserRole role, String full_name, String email) {
}