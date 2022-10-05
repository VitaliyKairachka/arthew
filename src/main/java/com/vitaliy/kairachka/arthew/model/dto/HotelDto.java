package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class HotelDto {

  private Long id;
  private String name;
  private Long numberCount;
  private Long photoCount;
  private Place place;
}
