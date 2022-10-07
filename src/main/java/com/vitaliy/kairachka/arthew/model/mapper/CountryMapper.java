package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper
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

  @Mapping(source = "name", target = "name")
  CountryDto toDtoFromRequest(CreateCountryRequest request);

  CreateCountryRequest toRequestFromDto(CountryDto dto);

  CountryDto merge(CountryDto source, Optional<Country> target);
}
