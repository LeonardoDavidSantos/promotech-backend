package com.promotech.api.mappers;

import com.promotech.api.domain.promotion.Promotion;
import com.promotech.api.domain.promotion.PromotionRequestDTO;
import com.promotech.api.domain.promotion.PromotionResponseDTO;
import com.promotech.api.domain.promotion.PromotionUpdateDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromotionMapper {

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(source = "title", target = "title"),
			@Mapping(source = "description", target = "description"),
			@Mapping(source = "price", target = "price"),
			@Mapping(source = "img_url", target = "imgUrl"),
			@Mapping(source = "link_url", target = "linkUrl")
	})
	Promotion toEntity(PromotionRequestDTO dto);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(source = "title", target = "title"),
			@Mapping(source = "description", target = "description"),
			@Mapping(source = "imgUrl", target = "img_url"),
			@Mapping(source = "linkUrl", target = "link_url"),
			@Mapping(source = "price", target = "price")
	})
	PromotionResponseDTO toDto(Promotion entity);

	@Mappings({
			@Mapping(target = "id", ignore = true),
			@Mapping(source = "title", target = "title"),
			@Mapping(source = "description", target = "description"),
			@Mapping(source = "price", target = "price"),
			@Mapping(source = "img_url", target = "imgUrl"),
			@Mapping(source = "link_url", target = "linkUrl"),
			@Mapping(source = "is_expired", target = "isExpired")
	})
	void updateEntityFromDto(PromotionUpdateDTO dto, @MappingTarget Promotion entity);
}
