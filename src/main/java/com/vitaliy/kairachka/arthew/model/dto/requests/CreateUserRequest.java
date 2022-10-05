package com.vitaliy.kairachka.arthew.model.dto.requests;

import com.vitaliy.kairachka.arthew.model.enums.Role;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
public class CreateUserRequest {

  private String login;
  private String password;
  private String fio;
  private Role role;
}
