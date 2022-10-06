package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.Place;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreateHotelRequest {

  private String name;
  private Place place;
}
