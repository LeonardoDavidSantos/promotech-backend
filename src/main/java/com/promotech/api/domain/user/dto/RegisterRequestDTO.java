package com.promotech.api.domain.user.dto;

import com.promotech.api.domain.user.UserRole;

public record RegisterRequestDTO(String username, String password, UserRole role) {
}
