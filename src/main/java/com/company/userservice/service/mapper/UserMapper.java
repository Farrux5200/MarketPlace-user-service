package com.company.userservice.service.mapper;

import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    public abstract User toEntity(UserDto dto);

    public abstract UserDto toDto(User user);

    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "deleteAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);
}
