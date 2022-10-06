package com.vitaliy.kairachka.arthew.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@NoArgsConstructor
public class CountryDto {

  private Long id;
  private String name;
  private Long regionCounter;
  private Long placeCounter;
  private Long hotelCounter;
}
