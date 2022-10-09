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
  public UserDto getById(@DestinationVariable Long id) {
    return userService.getUserById(id);
  }

  @MessageMapping("/user/{login}")
  @SendTo("topic/messages")
  public UserDto getByLogin(@DestinationVariable String login) {
    return userService.getUserByLogin(login);
  }

  @MessageMapping("/user/create")
  @SendTo("/topic/messages")
  public UserDto create(@Payload CreateUserRequest request) {
    return userService.createUser(request);
  }

  @MessageMapping("/user/update/{id}")
  @SendTo("/topic/messages")
  public UserDto update(@DestinationVariable Long id, @Payload UserDto userDto) {
    return userService.updateUser(id, userDto);
  }

  @MessageMapping("/user/delete/{id}")
  @SendTo("/topic/messages")
  public void delete(@DestinationVariable Long id) {
    userService.deleteUser(id);
  }
}
