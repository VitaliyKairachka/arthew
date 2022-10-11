package com.vitaliy.kairachka.arthew.service.impl;

import com.vitaliy.kairachka.arthew.model.dto.TaskDto;
import com.vitaliy.kairachka.arthew.model.dto.requests.create.CreateTaskRequest;
import com.vitaliy.kairachka.arthew.model.entity.Task;
import com.vitaliy.kairachka.arthew.model.mapper.TaskMapper;
import com.vitaliy.kairachka.arthew.repository.TaskRepository;
import com.vitaliy.kairachka.arthew.repository.UserRepository;
import com.vitaliy.kairachka.arthew.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private SimpMessagingTemplate messagingTemplate;
    private final TaskMapper taskMapper;


    @Override
    @Cacheable(value = "tasks")
    public Page<Task> getAllTasks(Pageable pageable) {
        log.info("Get all tasks");
        return taskRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "tasks")
    public TaskDto getTaskById(Long id) {
        var entity = taskRepository.findById(id);
        if (entity.isPresent()) {
            log.info("Get task with id: {}", id);
            return taskMapper.toDtoFromEntity(entity.get());
        } else {
            log.info("Task not found with id: {}", id);
            throw new RuntimeException(); //TODO
        }
    }

    @Override
    @Cacheable(value = "tasks")
    public TaskDto getTaskByName(String name) {
        var entity = taskRepository.findTaskByName(name);
        if (entity.isPresent()) {
            log.info("Get task with name: {}", name);
            return taskMapper.toDtoFromEntity(entity.get());
        } else {
            log.info("Task not found with name: {}", name);
            throw new RuntimeException(); //TODO
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskDto createTask(CreateTaskRequest request) {
        var taskDto = taskMapper.toDtoFromRequest(request);
        var entity = taskMapper.toEntityFromDto(taskDto);
        var savedEntity = taskRepository.save(entity);
        sendNotification(savedEntity);
        log.info("Create new task with name: {}", savedEntity.getName());
        return taskMapper.toDtoFromEntity(savedEntity);
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
            exception.printStackTrace(); //TODO
        }
    }

    public void notification(Task task) {
        messagingTemplate.convertAndSend("/topic/message/notification", task);
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        var target = taskRepository.findById(id);
        if (target.isPresent()) {
            var update = taskMapper.toEntityFromDto(taskMapper.merge(taskDto, target.get()));
            log.info("Task update with id: {}", id);
            return taskMapper.toDtoFromEntity(taskRepository.save(update));
        } else {
            log.info("Task not found with id: {}", id);
            throw new RuntimeException(); //TODO
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
