package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserService {

  Boolean login(LoginUserRequest request);

  List<UserDto> getAllUsers();

  UserDto getUserById(Long id);

  UserDto getUserByName(String name);

  UserDto createUser(CreateUserRequest createUserRequest);

  UserDto updateUser(Long id, UserDto userDto);

  void deleteUser(Long id);
}
