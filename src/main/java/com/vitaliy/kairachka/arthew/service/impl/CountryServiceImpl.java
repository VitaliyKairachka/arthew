package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;

  @Override
  public Page<CountryDto> getAllCountries(Pageable pageable) {
    return countryRepository.findAll(pageable);
  }

  @Override
  public CountryDto getCountryByName(String name) {
    return null;
  }

  @Override
  public CountryDto createCountry(CountryDto countryDto) {
    return null;
  }

  @Override
  public CountryDto updateCountry(CountryDto countryDto) {
    return null;
  }

  @Override
  public void deleteCountry(Long id) {

  }
}
