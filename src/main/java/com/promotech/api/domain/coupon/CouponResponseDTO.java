package com.promotech.api.domain.coupon;

import java.util.Date;
import java.util.UUID;

public record CouponResponseDTO(UUID id, String title, String description, String code, String link_url, Date created_at) {
}
