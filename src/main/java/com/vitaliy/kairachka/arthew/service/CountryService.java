package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryService {

  List<CountryDto> getAllCountries();

  CountryDto getCountryByName(String name);

  CountryDto createCountry(CountryDto countryDto);

  CountryDto updateCountry(Long id, CountryDto countryDto);

  void deleteCountry(Long id);
}
