package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  @MessageMapping("/user/login")
  @SendTo("/topic/user/login")
  public Boolean login(@Payload LoginUserRequest request) {
    return userService.login(request);
  }

  @MessageMapping("/user")
  @SendTo("/topic/messages")
  public List<UserDto> getAll() {
    return userService.getAllUsers();
  }

  @MessageMapping("/user/{id}")
  @SendTo("/topic/messages")
  public UserDto get(@DestinationVariable Long id) {
    return userService.getUserById(id);
  }

  @MessageMapping("/user/create")
  @SendTo("/topic/user/create")
  public UserDto create(@Payload CreateUserRequest request) {
    return userService.createUser(request);
  }
}
