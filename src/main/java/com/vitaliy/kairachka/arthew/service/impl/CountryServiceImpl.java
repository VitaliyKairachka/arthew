package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.CountryResponse;
import com.vitaliy.kairachka.arthew.model.mapper.CountryMapper;
import com.vitaliy.kairachka.arthew.repository.CountryRepository;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    @Cacheable(value = "countries")
    public List<CountryResponse> getAllCountries(Pageable pageable) {
        log.info("Get all countries");
        var list = countryRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(countryMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "countries")
    public CountryResponse getCountryById(Long id) {
        var entity = countryRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get country with id: {}", id);
            return countryMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Country not found with id: {}", id);
            return new CountryResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Cacheable(value = "countries")
    public CountryResponse getCountryByName(String name) {
        var entity = countryRepository.findCountryByName(name);
        if (entity.isPresent()) {
            log.info("Get country with name: {}", name);
            return countryMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Country not found with name: {}", name);
            return new CountryResponse()
                    .setName(name)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "countries", allEntries = true)
    public CountryResponse createCountry(CreateCountryRequest request) {
        var entity = countryMapper.toEntityFromRequest(request);
        log.info("Create new country with name: {}", entity.getName());
        return countryMapper.toResponseFromEntity(countryRepository.save(entity));
    }

    @Override
    @Transactional
    @CacheEvict(value = "countries", allEntries = true)
    public CountryResponse updateCountry(Long id, CountryDto countryDto) {
        var target = countryRepository.findById(id);
        if (target.isPresent()) {
            var update = countryMapper.toEntityFromDto(countryMapper.merge(target.get()));
            log.info("Country update with id: {}", id);
            return countryMapper.toResponseFromEntity(countryRepository.save(update));
        } else {
            log.info("Country not found with id: {}", id);
            return new CountryResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "countries", allEntries = true)
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
