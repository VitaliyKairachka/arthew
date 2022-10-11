package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface CountryService {

    List<Country> getAllCountries(Pageable pageable);

    CountryDto getCountryById(Long id);

    CountryDto getCountryByName(String name);

    CountryDto createCountry(CreateCountryRequest request);

    CountryDto updateCountry(Long id, CountryDto countryDto);

    void deleteCountry(Long id);
}
