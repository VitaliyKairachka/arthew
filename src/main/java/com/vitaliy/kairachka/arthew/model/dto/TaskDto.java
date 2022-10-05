package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class TaskDto {

  private Long id;
  private String name;
  private String description;
  private String notification; //TODO уведомление
  private User user;
}
