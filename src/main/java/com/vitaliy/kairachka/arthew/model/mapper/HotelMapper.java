package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateHotelRequest;
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
    Hotel toEntityFromDto(HotelDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "numberCount", target = "numberCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "place", target = "place")
    HotelDto toDtoFromEntity(Hotel entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "place", target = "place")
    HotelDto toDtoFromRequest(CreateHotelRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "numberCount", target = "numberCount")
    @Mapping(source = "photoCount", target = "photoCount")
    @Mapping(source = "place", target = "place")
    HotelDto merge(@MappingTarget HotelDto hotelDto, Hotel target);
}
