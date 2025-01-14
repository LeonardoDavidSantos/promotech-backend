package com.promotech.api.mappers;

import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.store.StoreRequestDTO;
import com.promotech.api.domain.store.StoreResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;  

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StoreMapper {  
    
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "description", target = "name"),
            @Mapping(source = "img_url", target = "imgUrl"),
            @Mapping(source = "link_url", target = "linkUrl"),
            @Mapping(source = "tag", target = "tag"),
    })
    Store toEntity(StoreRequestDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "description", target = "name"),
            @Mapping(source = "imgUrl", target = "img_url"),
            @Mapping(source = "linkUrl", target = "link_url"),
            @Mapping(source = "tag", target = "tag"),
    })
    StoreResponseDTO toDto(Store entity);
}
