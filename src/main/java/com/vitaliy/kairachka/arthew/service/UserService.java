package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.UserDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateUserRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.UserLoginRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.UserLoginResponse;
import com.vitaliy.kairachka.arthew.model.dto.response.UserResponse;
import com.vitaliy.kairachka.arthew.model.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface UserService {

    UserLoginResponse login(UserLoginRequest request);

    List<UserResponse> getAllUsers(Pageable pageable);

    UserResponse getUserById(Long id);

    UserResponse getUserByLogin(String name);

    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);
}
