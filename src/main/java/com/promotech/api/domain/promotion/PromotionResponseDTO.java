package com.promotech.api.domain.promotion;

import java.util.Date;
import java.util.UUID;

public record PromotionResponseDTO(UUID id, String title, String description, Float price, String img_url, String link_url, Date created_at) {
}
