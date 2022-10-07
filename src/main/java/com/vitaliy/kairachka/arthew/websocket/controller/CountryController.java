package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.CountryDto;
import com.vitaliy.kairachka.arthew.service.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class CountryController {

  private final CountryService countryService;

  public List<CountryDto> getAll() {
    return countryService.getAllCountries();
  }
}