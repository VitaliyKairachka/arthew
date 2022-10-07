package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.mapper.TaskMapper;
import com.vitaliy.kairachka.arthew.repository.TaskRepository;
import com.vitaliy.kairachka.arthew.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Vitaliy Kayrachka
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Override
  public Page<TaskDto> getAllTasks(Pageable pageable) {
    return null;
  }

  @Override
  public TaskDto getTaskByName(String name) {
    return null;
  }

  @Override
  public TaskDto createTask(TaskDto taskDto) {
    return null;
  }

  @Override
  public TaskDto updateTask(TaskDto taskDto) {
    return null;
  }

  @Override
  public void deleteTask(Long id) {

  }
}
