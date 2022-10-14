package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.PhotoResponse;
import com.vitaliy.kairachka.arthew.model.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    @Mapping(source = "place", target = "place")
    @Mapping(source = "hotel", target = "hotel")
    @Mapping(source = "number", target = "number")
    Photo toEntityFromDto(PhotoDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    @Mapping(source = "place", target = "place")
    @Mapping(source = "hotel", target = "hotel")
    @Mapping(source = "number", target = "number")
    PhotoDto toDtoFromEntity(Photo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    @Mapping(source = "place", target = "place")
    @Mapping(source = "hotel", target = "hotel")
    @Mapping(source = "number", target = "number")
    PhotoDto toDtoFromRequest(CreatePhotoRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    @Mapping(target = "isFound", ignore = true)
    @Mapping(source = "place", target = "place")
    @Mapping(source = "hotel", target = "hotel")
    @Mapping(source = "number", target = "number")
    PhotoResponse toResponseFromEntity(Photo entity);
}
