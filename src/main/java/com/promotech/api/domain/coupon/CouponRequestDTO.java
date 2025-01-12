package com.promotech.api.domain.coupon;

public record CouponRequestDTO(String title, String description, String code, String link_url) {
}