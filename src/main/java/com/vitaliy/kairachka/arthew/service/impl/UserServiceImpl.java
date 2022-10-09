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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    var result = getUserByLogin(request.getLogin());
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
    var entity = userRepository.findById(id);
    if (entity.isPresent()) {
      return userMapper.toDtoFromEntity(entity.get());
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  public UserDto getUserByLogin(String login) {
    var entity = userRepository.findUserByLogin(login);
    if (entity.isPresent()) {
      return userMapper.toDtoFromEntity(entity.get());
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  public UserDto createUser(CreateUserRequest createUserRequest) {
    var userDto = userMapper.toDtoFromRequest(createUserRequest);
    var entity = userMapper.toEntityFromDto(userDto);
    return userMapper.toDtoFromEntity(userRepository.save(entity));
  }

  @Override
  @Transactional
  public UserDto updateUser(Long id, UserDto userDto) {
    var target = userRepository.findById(id);
    if (target.isPresent()) {
      var update = userMapper.toEntityFromDto(userMapper.merge(userDto, target.get()));
      return userMapper.toDtoFromEntity(userRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }


  @Override
  @Transactional
  public void deleteUser(Long id) {
    var target = userRepository.findById(id);
    if (target.isPresent()) {
      userRepository.delete(target.get());
      log.info("User deleted with id: {}", id);
    } else {
      log.info("User not found with id: {}", id);
    }
  }

  @Override
  public UserDetails loadUserByUsername(String login) {
    var entity = userRepository.findUserByLogin(login);
    if (entity.isPresent()) {
      return entity.get();
    } else {
      throw new RuntimeException(); //TODO
    }
  }
}
