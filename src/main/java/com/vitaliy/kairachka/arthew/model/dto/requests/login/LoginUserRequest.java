package com.vitaliy.kairachka.arthew.model.dto.requests.login;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Kayrachka
 */
@Data
@AllArgsConstructor
public class LoginUserRequest {
    private String login;
    private String password;
}
