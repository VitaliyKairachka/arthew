package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.PlaceDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePlaceRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PlaceResponse;
import com.vitaliy.kairachka.arthew.model.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface PlaceMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "region", target = "region")
    Place toEntityFromDto(PlaceDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "region", target = "region")
    PlaceDto toDtoFromEntity(Place place);

    @Mapping(source = "name", target = "name")
    PlaceDto toDtoFromRequest(CreatePlaceRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(target = "hotelCount", ignore = true)
    @Mapping(target = "photoCount", ignore = true)
    @Mapping(target = "region", ignore = true)
    Place merge(@MappingTarget Place target, Place source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "region", target = "region")
    @Mapping(target = "isFound", ignore = true)
    PlaceResponse toResponseFromEntity(Place entity);
}
