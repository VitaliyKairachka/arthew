package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class CountryController {

  private final CountryService countryService;

  @MessageMapping("/country")
  @SendTo("/topic/messages")
  public Page<Country> getAll(@Payload Pageable pageable) {
    return countryService.getAllCountries(pageable);
  }

  @MessageMapping("/country/{id}")
  @SendTo("/topic/messages")
  public CountryDto getById(@DestinationVariable Long id) {
    return countryService.getCountryById(id);
  }

  @MessageMapping("/country/{name}")
  @SendTo("/topic/messages")
  public CountryDto getByName(@DestinationVariable String name) {
    return countryService.getCountryByName(name);
  }

  @MessageMapping("/country/create")
  @SendTo("/topic/messages")
  public CountryDto create(@Payload CreateCountryRequest request) {
    return countryService.createCountry(request);
  }

  @MessageMapping("/country/update/{id}")
  @SendTo("/topic/messages")
  public CountryDto update(@DestinationVariable Long id, @Payload CountryDto countryDto) {
    return countryService.updateCountry(id, countryDto);
  }

  @MessageMapping("/country/delete/{id}")
  @SendTo("/topic/messages")
  public void delete(@DestinationVariable Long id) {
    countryService.deleteCountry(id);
  }
}