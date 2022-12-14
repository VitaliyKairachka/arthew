package com.vitaliy.kairachka.arthew.websocket.controller;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.PageableRequest;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.TaskResponse;
import com.vitaliy.kairachka.arthew.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author Vitaliy Kayrachka
 */
@Controller
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @MessageMapping("/task")
    @SendTo("/topic/task")
    public List<TaskResponse> getAll(@Payload PageableRequest page) {
        Pageable pageable = PageRequest.of(page.getPage(), page.getSize());
        return taskService.getAllTasks(pageable);
    }

    @MessageMapping("/task/id/{id}")
    @SendTo("/topic/task")
    public TaskResponse getById(@DestinationVariable Long id) {
        return taskService.getTaskById(id);
    }

    @MessageMapping("/task/name/{name}")
    @SendTo("/topic/task")
    public TaskResponse getByName(@DestinationVariable String name) {
        return taskService.getTaskByName(name);
    }

    @MessageMapping("/task/create")
    @SendTo("/topic/task")
    public TaskResponse create(@Payload CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @MessageMapping("/task/update/{id}")
    @SendTo("/topic/task")
    public TaskResponse update(@DestinationVariable Long id, @Payload TaskDto taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @MessageMapping("/task/delete/{id}")
    @SendTo("/topic/task")
    public void delete(@DestinationVariable Long id) {
        taskService.deleteTask(id);
    }
}
