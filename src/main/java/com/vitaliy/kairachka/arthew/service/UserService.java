package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserService {

  Boolean login(LoginUserRequest request);

  Page<UserDto> getAllUsers(Pageable pageable);

  UserDto getUserById(Long id);

  UserDto getUserByName(String name);

  UserDto createUser(UserDto userDto);

  UserDto updateUser(UserDto userDto);

  void deleteUser(Long id);
}
