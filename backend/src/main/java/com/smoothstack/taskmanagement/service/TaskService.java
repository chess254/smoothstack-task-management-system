package com.smoothstack.taskmanagement.service;

import com.smoothstack.taskmanagement.dto.TaskDto;
import com.smoothstack.taskmanagement.entity.Task;
import com.smoothstack.taskmanagement.entity.User;
import com.smoothstack.taskmanagement.repository.TaskRepository;
import com.smoothstack.taskmanagement.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDto> getTasks(String status, Long assigneeId) {
        return taskRepository.findByStatusAndAssignee(status, assigneeId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto createTask(TaskDto taskDto) {
        Task task = toEntity(taskDto);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return toDto(taskRepository.save(task));
    }

    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        updateFromDto(task, taskDto);
        task.setUpdatedAt(LocalDateTime.now());
        return toDto(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean isTaskCreator(Long taskId, Long userId) {
        return taskRepository.findById(taskId)
                .map(task -> task.getCreator().getId().equals(userId))
                .orElse(false);
    }

    private Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        if (dto.getAssigneeId() != null) {
            User assignee = userRepository.findById(dto.getAssigneeId()).orElseThrow();
            task.setAssignee(assignee);
        }
        if (dto.getCreatorId() != null) {
            User creator = userRepository.findById(dto.getCreatorId()).orElseThrow();
            task.setCreator(creator);
        }
        return task;
    }

    private TaskDto toDto(Task entity) {
        TaskDto dto = new TaskDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setPriority(entity.getPriority());
        if (entity.getAssignee() != null) dto.setAssigneeId(entity.getAssignee().getId());
        if (entity.getCreator() != null) dto.setCreatorId(entity.getCreator().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    private void updateFromDto(Task task, TaskDto dto) {
        if (dto.getTitle() != null) task.setTitle(dto.getTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) {
            // Validate status transition
            if (!isValidTransition(task.getStatus(), dto.getStatus())) {
                throw new RuntimeException("Invalid status transition");
            }
            task.setStatus(dto.getStatus());
        }
        if (dto.getPriority() != null) task.setPriority(dto.getPriority());
        if (dto.getAssigneeId() != null) {
            User assignee = userRepository.findById(dto.getAssigneeId()).orElseThrow();
            task.setAssignee(assignee);
        }
    }

    private boolean isValidTransition(String from, String to) {
        if (from.equals("TODO") && (to.equals("IN_PROGRESS") || to.equals("DONE"))) return true;
        if (from.equals("IN_PROGRESS") && to.equals("DONE")) return true;
        return from.equals(to); // Allow same status
    }
}