package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface TaskService {

  List<TaskDto> getAllTasks();

  TaskDto getTaskById(Long id);

  TaskDto getTaskByName(String name);

  TaskDto createTask(CreateTaskRequest request);

  TaskDto updateTask(Long id, TaskDto taskDto);

  void deleteTask(Long id);
}
