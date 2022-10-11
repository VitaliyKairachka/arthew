package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @MessageMapping("/country")
    @SendTo("/topic/country")
    public List<Country> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return countryService.getAllCountries(pageable);
    }

    @MessageMapping("/country/id/{id}")
    @SendTo("/topic/country")
    public CountryDto getById(@DestinationVariable Long id) {
        return countryService.getCountryById(id);
    }

    @MessageMapping("/country/name/{name}")
    @SendTo("/topic/country")
    public CountryDto getByName(@DestinationVariable String name) {
        return countryService.getCountryByName(name);
    }

    @MessageMapping("/country/create")
    @SendTo("/topic/country")
    public CountryDto create(@Payload CreateCountryRequest request) {
        return countryService.createCountry(request);
    }

    @MessageMapping("/country/update/{id}")
    @SendTo("/topic/country")
    public CountryDto update(@DestinationVariable Long id, @Payload CountryDto countryDto) {
        return countryService.updateCountry(id, countryDto);
    }

    @MessageMapping("/country/delete/{id}")
    @SendTo("/topic/country")
    public void delete(@DestinationVariable Long id) {
        countryService.deleteCountry(id);
    }
}