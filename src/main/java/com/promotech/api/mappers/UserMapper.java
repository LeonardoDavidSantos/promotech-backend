package com.promotech.api.mappers;

import com.promotech.api.domain.user.User;
import com.promotech.api.domain.user.dto.UserPreviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    UserPreviewDTO toUserPreviewDto(User user);
}
