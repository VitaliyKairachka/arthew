package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface TaskService {

    List<Task> getAllTasks(Pageable pageable);

    TaskDto getTaskById(Long id);

    TaskDto getTaskByName(String name);

    TaskDto createTask(CreateTaskRequest request);

    TaskDto updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);
}
