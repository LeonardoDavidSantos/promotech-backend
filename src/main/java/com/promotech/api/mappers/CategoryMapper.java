package com.promotech.api.mappers;

import com.promotech.api.domain.category.Category;
import com.promotech.api.domain.category.CategoryRequestDTO;
import com.promotech.api.domain.category.CategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "tag", target = "tag")
    })
    Category toEntity(CategoryRequestDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "tag", target = "tag")
    })
    CategoryResponseDTO toDto(Category entity);
}
