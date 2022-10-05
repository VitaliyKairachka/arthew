package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class RegionDto {

  private Long id;
  private String name;
  private Long placeCount;
  private Long hotelCount;
  private Country country;
}
