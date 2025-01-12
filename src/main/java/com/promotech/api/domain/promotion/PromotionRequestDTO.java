package com.promotech.api.domain.promotion;

public record PromotionRequestDTO(String title, String description, Float price, String img_url, String link_url) {
}