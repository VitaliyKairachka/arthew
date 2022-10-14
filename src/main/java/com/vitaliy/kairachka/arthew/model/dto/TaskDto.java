package com.vitaliy.kairachka.arthew.model.dto;

import com.vitaliy.kairachka.arthew.model.entity.User;
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
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private String notification;
    private User user;
}
