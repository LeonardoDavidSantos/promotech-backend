package com.promotech.api.domain.user.dto;

import com.promotech.api.domain.user.UserRole;

public record LoginResponseDTO(String token, String username, UserRole role) {
}
