package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.NumberDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateNumberRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.HotelResponse;
import com.vitaliy.kairachka.arthew.model.dto.response.NumberResponse;
import com.vitaliy.kairachka.arthew.model.entity.Number;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface NumberMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "hotel", target = "hotel")
    Number toEntityFromDto(NumberDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "hotel", target = "hotel")
    NumberDto toDtoFromEntity(Number entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "photoCount", ignore = true)
    @Mapping(source = "hotel", target = "hotel")
    NumberDto toDtoFromRequest(CreateNumberRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "hotel", target = "hotel")
    NumberDto merge(NumberDto source, @MappingTarget Number target);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "hotel", target = "hotel")
    @Mapping(target = "isFound", defaultValue = "true")
    NumberResponse toResponseFromEntity(Number entity);
}
