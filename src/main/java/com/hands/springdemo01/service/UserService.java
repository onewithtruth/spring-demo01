package com.hands.springdemo01.service;

import com.hands.springdemo01.entity.User;
import com.hands.springdemo01.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public User saveUser(User user) {
        validateDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser != null) {
            throw new IllegalStateException("already_existing_email");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

}
