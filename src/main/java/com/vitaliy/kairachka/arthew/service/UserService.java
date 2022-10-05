package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserService {

  Page<UserDto> getAllUsers(Pageable pageable);

  UserDto getUserById(Long id);

  UserDto createUser(UserDto userDto);

  UserDto updateUser(UserDto userDto);

  void deleteUser(Long id);
}
