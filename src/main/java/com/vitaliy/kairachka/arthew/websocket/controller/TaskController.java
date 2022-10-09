package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import com.vitaliy.kairachka.arthew.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @MessageMapping("/task")
  @SendTo("/topic/messages")
  public Page<Task> getAll(@Payload Pageable pageable) {
    return taskService.getAllTasks(pageable);
  }

  @MessageMapping("/task/{id}")
  @SendTo("/topic/messages")
  public TaskDto getById(@DestinationVariable Long id) {
    return taskService.getTaskById(id);
  }

  @MessageMapping("/task/{name}")
  @SendTo("/topic/messages")
  public TaskDto getByName(@DestinationVariable String name) {
    return taskService.getTaskByName(name);
  }

  @MessageMapping("/task/create")
  @SendTo("/topic/messages")
  public TaskDto create(@Payload CreateTaskRequest request) {
    return taskService.createTask(request);
  }

  @MessageMapping("/task/update/{id}")
  @SendTo("/topic/messages")
  public TaskDto update(@DestinationVariable Long id, @Payload TaskDto taskDto) {
    return taskService.updateTask(id, taskDto);
  }

  @MessageMapping("/task/update/{id}")
  @SendTo("/topic/messages")
  public void delete(@DestinationVariable Long id) {
    taskService.deleteTask(id);
  }
}
