package com.smoothstack.taskmanagement.security;

import com.smoothstack.taskmanagement.entity.User;
import com.smoothstack.taskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new UserDetailsImpl(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return new UserDetailsImpl(user);
    }
}

class UserDetailsImpl extends org.springframework.security.core.userdetails.User {
    private final Long id;

    public UserDetailsImpl(User user) {
        super(user.getEmail(), user.getPassword(), user.getAuthorities());
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }
}