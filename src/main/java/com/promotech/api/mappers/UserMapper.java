package com.promotech.api.mappers;

import com.promotech.api.domain.user.User;
import com.promotech.api.domain.user.dto.UserPreviewDTO;
import com.promotech.api.domain.user.dto.UserResponseDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    UserPreviewDTO toUserPreviewDto(User user);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "fullName", target = "full_name"),
            @Mapping(source = "email", target = "email")
    })
    UserResponseDTO toDto(User user);
}
