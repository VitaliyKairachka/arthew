package com.vitaliy.kairachka.arthew.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String notification; //TODO уведомление

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private User user;
}
