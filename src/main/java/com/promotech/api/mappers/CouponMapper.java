package com.promotech.api.mappers;

import com.promotech.api.domain.coupon.Coupon;
import com.promotech.api.domain.coupon.CouponRequestDTO;
import com.promotech.api.domain.coupon.CouponResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CouponMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "link_url", target = "linkUrl")
    })
    Coupon toEntity(CouponRequestDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "linkUrl", target = "link_url"),
            @Mapping(source = "isExpired", target = "is_expired"),
            @Mapping(source = "createdAt", target = "created_at"),
    })
    CouponResponseDTO toDto(Coupon entity);
}
