package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.dto.response.TaskResponse;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import com.vitaliy.kairachka.arthew.model.mapper.TaskMapper;
import com.vitaliy.kairachka.arthew.repository.TaskRepository;
import com.vitaliy.kairachka.arthew.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaliy Kayrachka
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private SimpMessagingTemplate messagingTemplate;
    private final TaskMapper taskMapper;


    @Override
    @Cacheable(value = "tasks")
    public List<TaskResponse> getAllTasks(Pageable pageable) {
        log.info("Get all tasks");
        var list = taskRepository.findAll(pageable).toList();
        return list
                .stream()
                .map(taskMapper::toResponseFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "tasks")
    public TaskResponse getTaskById(Long id) {
        var entity = taskRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get task with id: {}", id);
            return taskMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Task not found with id: {}", id);
            return new TaskResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Cacheable(value = "tasks")
    public TaskResponse getTaskByName(String name) {
        var entity = taskRepository.findTaskByName(name);
        if (entity.isPresent()) {
            log.info("Get task with name: {}", name);
            return taskMapper.toResponseFromEntity(entity.get());
        } else {
            log.info("Task not found with name: {}", name);
            return new TaskResponse()
                    .setName(name)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskResponse createTask(CreateTaskRequest request) {
        var taskDto = taskMapper.toDtoFromRequest(request);
        var entity = taskMapper.toEntityFromDto(taskDto);
        var savedEntity = taskRepository.save(entity);
        sendNotification(savedEntity);
        log.info("Create new task with name: {}", savedEntity.getName());
        return taskMapper.toResponseFromEntity(savedEntity);
    }

    private void sendNotification(Task task) {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            JobDetail job = JobBuilder
                    .newJob(((Job) context -> notification(task)).getClass())
                    .withIdentity("notification")
                    .build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("notification")
                    .withSchedule(CronScheduleBuilder.cronSchedule(task.getNotification()))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException exception) {
            exception.printStackTrace();
        }
    }

    public void notification(Task task) {
        messagingTemplate.convertAndSend("/topic/message/notification", task);
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskResponse updateTask(Long id, TaskDto taskDto) {
        var target = taskRepository.findById(id);
        var updateTask = taskMapper.toEntityFromDto(taskDto);
        if (target.isPresent()) {
            var update = taskMapper.merge(target.get(), updateTask);
            log.info("Task update with id: {}", id);
            return taskMapper.toResponseFromEntity(taskRepository.save(update));
        } else {
            log.info("Task not found with id: {}", id);
            return new TaskResponse()
                    .setId(id)
                    .setIsFound(false);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
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
