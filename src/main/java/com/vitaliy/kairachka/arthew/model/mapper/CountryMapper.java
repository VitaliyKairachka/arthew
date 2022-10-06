package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import java.util.Optional;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryMapper {

  Country toEntityFromDto(CountryDto dto);

  CountryDto toDtoFromEntity(Country entity);

  CountryDto toDtoFromRequest(CreateCountryRequest request);

  CreateCountryRequest toRequestFromDto(CountryDto dto);

  CountryDto merge(CountryDto source, Optional<Country> target);
}
