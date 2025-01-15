package com.promotech.api.mappers;

import com.promotech.api.domain.store.Store;
import com.promotech.api.domain.store.StoreRequestDTO;
import com.promotech.api.domain.store.StoreResponseDTO;
import com.promotech.api.domain.store.StoreUpdateDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
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
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "imgUrl", target = "img_url"),
            @Mapping(source = "linkUrl", target = "link_url"),
            @Mapping(source = "tag", target = "tag"),
    })
    StoreResponseDTO toDto(Store entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "img_url", target = "imgUrl"),
            @Mapping(source = "link_url", target = "linkUrl"),
            @Mapping(source = "tag", target = "tag"),
    })
    void updateEntityFromDto(StoreUpdateDTO dto, @MappingTarget Store entity);
}
