package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String login;
  private String password;
  private String fio;
  private Role role;
}
