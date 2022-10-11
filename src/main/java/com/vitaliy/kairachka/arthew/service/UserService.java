package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.login.LoginUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.login.ResponseUserLogin;
import com.vitaliy.kairachka.arthew.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserService {

    ResponseUserLogin login(LoginUserRequest request);

    List<User> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto getUserByLogin(String name);

    UserDto createUser(CreateUserRequest createUserRequest);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
