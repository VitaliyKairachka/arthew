package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.RegionDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateRegionRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.RegionResponse;
import com.vitaliy.kairachka.arthew.model.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface RegionMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "placeCount", target = "placeCount")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "country", target = "country")
    Region toEntityFromDto(RegionDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "placeCount", target = "placeCount")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "country", target = "country")
    RegionDto toDtoFromEntity(Region region);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "country", target = "country")
    RegionDto toDtoFromRequest(CreateRegionRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(target = "placeCount", ignore = true)
    @Mapping(target = "hotelCount", ignore = true)
    @Mapping(target = "country", ignore = true)
    Region merge(@MappingTarget Region target, Region source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "placeCount", target = "placeCount")
    @Mapping(source = "hotelCount", target = "hotelCount")
    @Mapping(source = "country", target = "country")
    @Mapping(target = "isFound", ignore = true)
    RegionResponse toResponseFromEntity(Region entity);
}
