package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.mapper.CountryMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.service.CountryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
  private final CountryMapper countryMapper;

  @Override
  public List<CountryDto> getAllCountries() {
    return countryRepository.findAll()
        .stream()
        .map(countryMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public CountryDto getCountryByName(String name) {
    var entity = countryRepository.findCountryByName(name);
    return countryMapper.toDtoFromEntity(entity);
  }

  @Override
  @Transactional
  public CountryDto createCountry(CreateCountryRequest request) {
    var countryDto = countryMapper.toDtoFromRequest(request);
    var entity = countryMapper.toEntityFromDto(countryDto);
    return countryMapper.toDtoFromEntity(countryRepository.save(entity));
  }

  @Override
  @Transactional
  public CountryDto updateCountry(Long id, CountryDto countryDto) {
    var target = countryRepository.findById(id);
    if (target.isPresent()) {
      var update = countryMapper.toEntityFromDto(countryMapper.merge(countryDto, target.get()));
      return countryMapper.toDtoFromEntity(countryRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  public void deleteCountry(Long id) {
    var target = countryRepository.findById(id);
    if (target.isPresent()) {
      countryRepository.delete(target.get());
      log.info("Country deleted with id: {}", id);
    } else {
      log.info("Country not found with id: {}", id);
    }
  }
}
