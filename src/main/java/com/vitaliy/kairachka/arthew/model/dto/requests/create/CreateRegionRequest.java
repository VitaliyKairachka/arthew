package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.Country;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreateRegionRequest {

  private String name;
  private Country country;
}
