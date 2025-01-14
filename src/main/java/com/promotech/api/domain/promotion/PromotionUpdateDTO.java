package com.promotech.api.domain.promotion;

import java.util.UUID;

public record PromotionUpdateDTO(String title, String description, Float price, String img_url, String link_url, UUID category_id, UUID store_id, boolean is_expired) {
}