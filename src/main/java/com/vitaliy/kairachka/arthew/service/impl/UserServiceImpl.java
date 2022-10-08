package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.model.mapper.UserMapper;
import com.vitaliy.kairachka.arthew.repository.UserRepository;
import com.vitaliy.kairachka.arthew.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Boolean login(LoginUserRequest request) {
    var result = getUserByName(request.getLogin());
    return result != null;
  }

  @Override
  public List<UserDto> getAllUsers() {
    return null;
  }

  @Override
  public UserDto getUserById(Long id) {
    return userMapper.toDtoFromEntity(userRepository.findById(id).get());
  }

  @Override
  public UserDto getUserByName(String name) {
    return userMapper.toDtoFromEntity(userRepository.findUserByLogin(name));
  }

  @Override
  public UserDto createUser(CreateUserRequest createUserRequest) {
    return null;
  }

  @Override
  public UserDto updateUser(Long id, UserDto userDto) {
    return null;
  }


  @Override
  public void deleteUser(Long id) {

  }
}
