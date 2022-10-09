package com.vitaliy.kairachka.arthew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArthewApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArthewApplication.class, args);
  }

}
