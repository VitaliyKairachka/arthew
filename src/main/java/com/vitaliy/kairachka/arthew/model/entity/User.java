package com.vitaliy.kairachka.arthew.model.entity;

import com.vitaliy.kairachka.arthew.model.enums.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String login;
  private String password;
  private String fio;

  @Enumerated(EnumType.STRING)
  private Role role;
}
