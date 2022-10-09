package com.vitaliy.kairachka.arthew.service.impl;

import static com.vitaliy.kairachka.arthew.utils.PasswordEncryption.checkPassword;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.ResponseUserLogin;
import com.vitaliy.kairachka.arthew.model.mapper.UserMapper;
import com.vitaliy.kairachka.arthew.repository.UserRepository;
import com.vitaliy.kairachka.arthew.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public ResponseUserLogin login(LoginUserRequest request) {
    var result = getUserByLogin(request.getLogin());
    return new ResponseUserLogin()
        .setIsSuccess(checkPassword(request.getPassword(), result.getPassword()))
        .setId(result.getId())
        .setLogin(result.getLogin())
        .setRole(result.getRole());
  }

  @Override
  @Cacheable(value = "users")
  public List<UserDto> getAllUsers() {
    log.info("Get all users");
    return userRepository.findAll()
        .stream()
        .map(userMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  @Cacheable(value = "users")
  public UserDto getUserById(Long id) {
    var entity = userRepository.findById(id);
    if (entity.isPresent()) {
      log.info("Get user with id: {}", id);
      return userMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("User not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Cacheable(value = "users")
  public UserDto getUserByLogin(String login) {
    var entity = userRepository.findUserByLogin(login);
    if (entity.isPresent()) {
      log.info("Get user with login: {}", login);
      return userMapper.toDtoFromEntity(entity.get());
    } else {
      log.info("User not found with login: {}", login);
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  @CacheEvict(value = "users", allEntries = true)
  public UserDto createUser(CreateUserRequest createUserRequest) {
    var userDto = userMapper.toDtoFromRequest(createUserRequest);
    var entity = userMapper.toEntityFromDto(userDto);
    log.info("Create new user with login: {}", entity.getLogin());
    return userMapper.toDtoFromEntity(userRepository.save(entity));
  }

  @Override
  @Transactional
  @CacheEvict(value = "users", allEntries = true)
  public UserDto updateUser(Long id, UserDto userDto) {
    var target = userRepository.findById(id);
    if (target.isPresent()) {
      var update = userMapper.toEntityFromDto(userMapper.merge(userDto, target.get()));
      log.info("User update with id: {}", id);
      return userMapper.toDtoFromEntity(userRepository.save(update));
    } else {
      log.info("User not found with id: {}", id);
      throw new RuntimeException(); //TODO
    }
  }


  @Override
  @Transactional
  @CacheEvict(value = "users", allEntries = true)
  public void deleteUser(Long id) {
    var target = userRepository.findById(id);
    if (target.isPresent()) {
      userRepository.delete(target.get());
      log.info("User deleted with id: {}", id);
    } else {
      log.info("User not found with id: {}", id);
    }
  }
}
