package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.CreateCountryRequest;
import com.vitaliy.kairachka.arthew.model.entity.Country;
import com.vitaliy.kairachka.arthew.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class CountryController {
  private final CountryService countryService;

  @MessageMapping("country/create")
  @SendTo("topic/country/create")
  public CountryDto create(CreateCountryRequest request) {
    return countryService.createCountry(request);
  }
}
