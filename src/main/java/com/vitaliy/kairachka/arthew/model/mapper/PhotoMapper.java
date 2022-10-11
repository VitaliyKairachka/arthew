package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.PhotoDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreatePhotoRequest;
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
    Photo toEntityFromDto(PhotoDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    PhotoDto toDtoFromEntity(Photo entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "file", target = "file")
    PhotoDto toDtoFromRequest(CreatePhotoRequest request);
}
