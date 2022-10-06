package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.Region;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreatePlaceRequest {

  private String name;
  private Region region;
}
