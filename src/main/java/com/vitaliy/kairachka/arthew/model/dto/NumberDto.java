package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class NumberDto {

  private Long id;
  private String name;
  private String description;
  private Long photoCount;
  private Hotel hotel;
}
