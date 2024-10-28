package com.promotech.api.domain.user;

public record RegisterRequestDTO(String username, String password, UserRole role) {
}
