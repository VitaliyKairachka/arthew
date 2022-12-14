package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.HotelResponse;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface HotelMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "numberCount", target = "numberCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "place", target = "place")
    @Mapping(target = "photoSet", ignore = true)
    Hotel toEntityFromDto(HotelDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "numberCount", target = "numberCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "place", target = "place")
    HotelDto toDtoFromEntity(Hotel entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "place", target = "place")
    @Mapping(target = "numberCount", ignore = true)
    @Mapping(target = "photoCount", ignore = true)
    HotelDto toDtoFromRequest(CreateHotelRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(target = "numberCount", ignore = true)
    @Mapping(target = "photoCount", ignore = true)
    @Mapping(target = "place", ignore = true)
    Hotel merge(@MappingTarget Hotel target, Hotel source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "numberCount", target = "numberCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "place", target = "place")
    @Mapping(target = "isFound", ignore = true)
    HotelResponse toResponseFromEntity(Hotel entity);
}