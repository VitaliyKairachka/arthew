package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @MessageMapping("/login")
  @SendTo("/topic/login")
  public Boolean login(@Payload LoginUserRequest request) {
    System.out.println("yess");
    return userService.login(request);
  }
}
