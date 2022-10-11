package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.UserLoginRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.UserLoginResponse;
import com.vitaliy.kairachka.arthew.model.dto.response.UserResponse;
import com.vitaliy.kairachka.arthew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @MessageMapping("/user/login")
    @SendTo("/topic/user/login")
    public UserLoginResponse login(@Payload UserLoginRequest request) {
        return userService.login(request);
    }

    @MessageMapping("/user")
    @SendTo("/topic/messages")
    public List<UserResponse> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return userService.getAllUsers(pageable);
    }

    @MessageMapping("/user/{id}")
    @SendTo("/topic/messages")
    public UserResponse getById(@DestinationVariable Long id) {
        return userService.getUserById(id);
    }

    @MessageMapping("/user/{login}")
    @SendTo("topic/messages")
    public UserResponse getByLogin(@DestinationVariable String login) {
        return userService.getUserByLogin(login);
    }

    @MessageMapping("/user/create")
    @SendTo("/topic/messages")
    public UserResponse create(@Payload CreateUserRequest request) {
        return userService.createUser(request);
    }

    @MessageMapping("/user/update/{id}")
    @SendTo("/topic/messages")
    public UserResponse update(@DestinationVariable Long id, @Payload UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @MessageMapping("/user/delete/{id}")
    @SendTo("/topic/messages")
    public void delete(@DestinationVariable Long id) {
        userService.deleteUser(id);
    }
}
