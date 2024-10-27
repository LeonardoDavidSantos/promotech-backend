package com.promotech.api.domain.user;

public record RegisterDTO(String username, String password, UserRole role) {
}
