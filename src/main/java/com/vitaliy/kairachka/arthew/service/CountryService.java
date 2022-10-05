package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryService {

  Page<CountryDto> getAllCountries(Pageable pageable);

  CountryDto getCountryByName(String name);

  CountryDto createCountry(CountryDto countryDto);

  CountryDto updateCountry(CountryDto countryDto);

  void deleteCountry(Long id);
}
