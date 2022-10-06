package com.vitaliy.kairachka.arthew.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vitaliy Kayrachka
 */
@Getter
@Setter
@NoArgsConstructor
public class CountryDto {

  private Long id;
  private String name;
  private Long regionCounter;
  private Long placeCounter;
  private Long hotelCounter;
}
