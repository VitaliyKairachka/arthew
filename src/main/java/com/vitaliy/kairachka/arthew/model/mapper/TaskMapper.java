package com.vitaliy.kairachka.arthew.model.mapper;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.TaskResponse;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author Vitaliy Kayrachka
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "user", target = "user")
    Task toEntityFromDto(TaskDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "user", target = "user")
    TaskDto toDtoFromEntity(Task entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "user", target = "user")
    TaskDto toDtoFromRequest(CreateTaskRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "notification", target = "notification")
    @Mapping(target = "user", ignore = true)
    Task merge(@MappingTarget Task target, Task source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "notification", target = "notification")
    @Mapping(source = "user", target = "user")
    @Mapping(target = "isFound", ignore = true)
    TaskResponse toResponseFromEntity(Task entity);
}
