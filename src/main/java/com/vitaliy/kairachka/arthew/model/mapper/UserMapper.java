package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.UserResponse;
import com.vitaliy.kairachka.arthew.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "role", target = "role")
    User toEntityFromDto(UserDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "role", target = "role")
    UserDto toDtoFromEntity(User entity);

    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "role", target = "role")
    UserDto toDtoFromRequest(CreateUserRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "role", target = "role")
    UserDto merge(UserDto source, @MappingTarget User user);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "login", target = "login")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "fio", target = "fio")
    @Mapping(source = "role", target = "role")
    @Mapping(target = "isFound", defaultValue = "true")
    UserResponse toResponseFromEntity(User entity);
}
