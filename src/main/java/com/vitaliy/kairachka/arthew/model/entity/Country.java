package com.vitaliy.kairachka.arthew.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@Entity
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Long regionCounter;
  private Long placeCounter;
  private Long hotelCounter;
}
