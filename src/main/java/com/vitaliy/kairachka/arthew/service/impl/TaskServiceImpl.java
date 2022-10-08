package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.mapper.TaskMapper;
import com.vitaliy.kairachka.arthew.repository.TaskRepository;
import com.vitaliy.kairachka.arthew.repository.UserRepository;
import com.vitaliy.kairachka.arthew.service.TaskService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;
  private final TaskMapper taskMapper;


  @Override
  public List<TaskDto> getAllTasks() {
    return taskRepository.findAll()
        .stream()
        .map(taskMapper::toDtoFromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public TaskDto getTaskByName(String name) {
    var entity = taskRepository.findTaskByName(name);
    return taskMapper.toDtoFromEntity(entity);
  }

  @Override
  @Transactional
  public TaskDto createTask(CreateTaskRequest request) {
    //TODO
    return null;
  }

  @Override
  @Transactional
  public TaskDto updateTask(Long id, TaskDto taskDto) {
    var target = taskRepository.findById(id);
    if (target.isPresent()) {
      var update = taskMapper.toEntityFromDto(taskMapper.merge(taskDto, target.get()));
      return taskMapper.toDtoFromEntity(taskRepository.save(update));
    } else {
      throw new RuntimeException(); //TODO
    }
  }

  @Override
  @Transactional
  public void deleteTask(Long id) {
    var target = taskRepository.findById(id);
    if (target.isPresent()) {
      taskRepository.delete(target.get());
      log.info("Task deleted with id: {}", id);
    } else {
      log.info("Task not found with id: {}", id);
    }
  }
}
