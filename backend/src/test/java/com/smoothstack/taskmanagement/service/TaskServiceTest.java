package com.smoothstack.taskmanagement.service;

import com.smoothstack.taskmanagement.dto.TaskDto;
import com.smoothstack.taskmanagement.entity.Task;
import com.smoothstack.taskmanagement.entity.User;
import com.smoothstack.taskmanagement.repository.TaskRepository;
import com.smoothstack.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() {
        TaskDto dto = new TaskDto();
        dto.setTitle("Test");
        dto.setStatus("TODO");
        dto.setPriority(1);
        dto.setCreatorId(1L);

        User creator = new User();
        creator.setId(1L);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test");
        task.setStatus("TODO");
        task.setPriority(1);
        task.setCreator(creator);

        when(userRepository.findById(1L)).thenReturn(Optional.of(creator));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        TaskDto result = taskService.createTask(dto);
        assertEquals(1L, result.getId());
        assertEquals("Test", result.getTitle());
    }

    @Test
    void updateTaskInvalidTransition() {
        Task existing = new Task();
        existing.setId(1L);
        existing.setStatus("DONE");

        TaskDto dto = new TaskDto();
        dto.setStatus("TODO");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> taskService.updateTask(1L, dto));
    }
}