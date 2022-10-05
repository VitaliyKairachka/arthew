package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Vitaliy Kayrachka
 */
public interface TaskService {

  Page<TaskDto> getAllTasks(Pageable pageable);

  TaskDto getTaskByName(String name);

  TaskDto createTask(TaskDto taskDto);

  TaskDto updateTask(TaskDto taskDto);

  void deleteTask(Long id);
}
