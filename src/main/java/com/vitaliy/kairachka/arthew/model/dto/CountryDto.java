package com.vitaliy.kairachka.arthew.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class CountryDto {

  private Long id;
  private String name;
  private Long regionCounter;
  private Long placeCounter;
  private Long hotelCounter;
}
