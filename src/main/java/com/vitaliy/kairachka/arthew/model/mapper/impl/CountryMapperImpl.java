package com.vitaliy.kairachka.arthew.model.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import com.vitaliy.kairachka.arthew.model.mapper.CountryMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Vitaliy Kayrachka
 */
@Component
@RequiredArgsConstructor
public class CountryMapperImpl implements CountryMapper {

  private final ObjectMapper objectMapper;

  @Override
  public Country toEntityFromDto(CountryDto dto) {
    var result = new Country();

    result.setId(dto.getId());
    result.setName(dto.getName());
    result.setRegionCounter(dto.getRegionCounter());
    result.setPlaceCounter(dto.getPlaceCounter());
    result.setHotelCounter(dto.getHotelCounter());

    return result;
  }

  @Override
  public CountryDto toDtoFromEntity(Country entity) {
    return null;
  }

  @Override
  public CountryDto toDtoFromRequest(CreateCountryRequest request) {
    return null;
  }

  @Override
  public CreateCountryRequest toRequestFromDto(CountryDto dto) {
    return null;
  }

  @Override
  public CountryDto merge(CountryDto source, Optional<Country> target) {
    if (target.isPresent()) {
      var targetDto = toDtoFromEntity(target.get());
      targetDto.setId(source.getId());
      targetDto.setName(source.getName());
      targetDto.setRegionCounter(source.getRegionCounter());
      targetDto.setPlaceCounter(source.getPlaceCounter());
      targetDto.setHotelCounter(source.getHotelCounter());
      return targetDto;
    }
  }
}
