package com.promotech.api.domain.store;

import java.util.UUID;

public record StoreResponseDTO(UUID id, String name, String description, String img_url, String link_url, String tag) {
}