package com.vitaliy.kairachka.arthew.service;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.TaskResponse;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
public interface TaskService {

    List<TaskResponse> getAllTasks(Pageable pageable);

    TaskResponse getTaskById(Long id);

    TaskResponse getTaskByName(String name);

    TaskResponse createTask(CreateTaskRequest request);

    TaskResponse updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);
}
