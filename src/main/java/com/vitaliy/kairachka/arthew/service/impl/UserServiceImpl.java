package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.model.mapper.UserMapper;
import com.vitaliy.kairachka.arthew.repository.UserRepository;
import com.vitaliy.kairachka.arthew.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Boolean login(LoginUserRequest request) {
    var result = getUserByName(request.getLogin());
    return result != null;
  }

  @Override
  public List<UserDto> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(userMapper::toDtoFromEntity)
        .collect(Collectors.toList());
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

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    return userRepository.findUserByLogin(login);
  }
}
