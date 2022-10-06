package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.mapper.CountryMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
  private final CountryMapper countryMapper;

  @Override
  public Page<CountryDto> getAllCountries(Pageable pageable) {
    return countryRepository.findAll(pageable);
  }

  @Override
  public CountryDto getCountryByName(String name) {

  }

  @Override
  @Transactional
  public CountryDto createCountry(CountryDto countryDto) {
    var entity = countryMapper.toEntityFromDto(countryDto);
    return countryMapper.toDtoFromEntity(countryRepository.save(entity));
  }

  @Override
  @Transactional
  public CountryDto updateCountry(Long id, CountryDto countryDto) {
    var target = countryRepository.findById(id);
    var update = countryMapper.toEntityFromDto(countryMapper.merge(countryDto, target));
    return countryMapper.toDtoFromEntity(countryRepository.save(update));
  }

  @Override
  @Transactional
  public void deleteCountry(Long id) {

  }
}
