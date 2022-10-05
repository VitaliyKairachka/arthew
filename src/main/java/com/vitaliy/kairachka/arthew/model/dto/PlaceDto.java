package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class PlaceDto {

  private Long id;
  private String name;
  private Long hotelCount;
  private Long photoCount;
  private Region region;
}
