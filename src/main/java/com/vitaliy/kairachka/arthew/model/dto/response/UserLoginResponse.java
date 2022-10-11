package com.vitaliy.kairachka.arthew.model.dto.response;

import com.vitaliy.kairachka.arthew.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Vitaliy Kayrachka
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserLoginResponse {
    private Boolean isSuccess;
    private Long id;
    private String login;
    private String password;
    private String fio;
    private Role role;
}
