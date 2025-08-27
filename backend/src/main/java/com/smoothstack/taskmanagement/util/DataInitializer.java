package com.smoothstack.taskmanagement.util;

import com.smoothstack.taskmanagement.entity.Task;
import com.smoothstack.taskmanagement.entity.User;
import com.smoothstack.taskmanagement.repository.TaskRepository;
import com.smoothstack.taskmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole("ADMIN");
        admin = userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRole("USER");
        user = userRepository.save(user);

        // Sample tasks
        createSampleTask("Task 1", "Description 1", "TODO", 1, null, admin);
        createSampleTask("Task 2", "Description 2", "TODO", 2, user, admin);
        createSampleTask("Task 3", "Description 3", "IN_PROGRESS", 3, admin, user);
        createSampleTask("Task 4", "Description 4", "IN_PROGRESS", 1, user, user);
        createSampleTask("Task 5", "Description 5", "DONE", 2, admin, admin);
        createSampleTask("Task 6", "Description 6", "DONE", 3, null, user);
    }

    private void createSampleTask(String title, String desc, String status, int priority, User assignee, User creator) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(desc);
        task.setStatus(status);
        task.setPriority(priority);
        task.setAssignee(assignee);
        task.setCreator(creator);
        taskRepository.save(task);
    }
}