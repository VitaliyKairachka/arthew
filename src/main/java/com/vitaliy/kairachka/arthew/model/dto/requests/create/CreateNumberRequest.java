package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.Hotel;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreateNumberRequest {

  private String name;
  private String description;
  private Hotel hotel;
}
