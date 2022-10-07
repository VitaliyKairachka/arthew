package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  @MessageMapping("/user/login")
  @SendTo("/topic/user/login")
  public Boolean login(@Payload LoginUserRequest request) {
    return userService.login(request);
  }

  @MessageMapping("/user/getAll")
  @SendTo("/topic/user/getAll")
  public Page<UserDto> getAll(@Payload Pageable pageable) {
    return userService.getAllUsers(pageable);
  }

  @MessageMapping("/user/get")
  @SendTo("/topic/user/get")
  public UserDto get(@Payload Long id) {
    return userService.getUserById(id);
  }

  @MessageMapping("/user/create")
  @SendTo("/topic/user/create")
  public UserDto create(@Payload CreateUserRequest request) {
    return userService.createUser(request);
  }
}
