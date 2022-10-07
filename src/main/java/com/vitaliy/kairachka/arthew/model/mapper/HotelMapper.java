package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.HotelDto;
import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper
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
}
