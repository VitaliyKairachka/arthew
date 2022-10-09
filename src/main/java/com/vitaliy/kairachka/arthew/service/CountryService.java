package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryService {

  List<CountryDto> getAllCountries();

  CountryDto getCountryById(Long id);

  CountryDto getCountryByName(String name);

  CountryDto createCountry(CreateCountryRequest request);

  CountryDto updateCountry(Long id, CountryDto countryDto);

  void deleteCountry(Long id);
}
