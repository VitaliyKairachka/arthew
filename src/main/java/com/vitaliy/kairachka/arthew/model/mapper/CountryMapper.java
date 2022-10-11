package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "regionCounter", target = "regionCounter")
    @Mapping(source = "placeCounter", target = "placeCounter")
    @Mapping(source = "hotelCounter", target = "hotelCounter")
    Country toEntityFromDto(CountryDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "regionCounter", target = "regionCounter")
    @Mapping(source = "placeCounter", target = "placeCounter")
    @Mapping(source = "hotelCounter", target = "hotelCounter")
    CountryDto toDtoFromEntity(Country entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(target = "regionCounter", ignore = true)
    @Mapping(target = "placeCounter", ignore = true)
    @Mapping(target = "hotelCounter", ignore = true)
    CountryDto toDtoFromRequest(CreateCountryRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "regionCounter", target = "regionCounter")
    @Mapping(source = "placeCounter", target = "placeCounter")
    @Mapping(source = "hotelCounter", target = "hotelCounter")
    CountryDto merge(@MappingTarget CountryDto source, Country target);
}
