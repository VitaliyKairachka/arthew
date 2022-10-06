package com.vitaliy.kairachka.arthew.model.dto.requests.create;

import com.vitaliy.kairachka.arthew.model.entity.User;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreateTaskRequest {

  private String name;
  private String description;
  private String notification;
  private User user;
}
