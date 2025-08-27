package com.smoothstack.taskmanagement.service;

import com.smoothstack.taskmanagement.dto.RegisterRequest;
import com.smoothstack.taskmanagement.entity.User;
import com.smoothstack.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerNewUser() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("test");
        request.setEmail("test@smoothstack.com");
        request.setPassword("pass");
        request.setRole("USER");

        when(userRepository.findByEmail("test@smoothstack.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("pass")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User result = userService.register(request);
        assertEquals("test", result.getUsername());
        assertEquals("encoded", result.getPassword());
    }

    @Test
    void registerExistingEmail() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("existing@smoothstack.com");

        when(userRepository.findByEmail("existing@smoothstack.com")).thenReturn(Optional.of(new User()));

        assertThrows(RuntimeException.class, () -> userService.register(request));
    }
}