package com.hands.springdemo01.service;

import com.hands.springdemo01.dto.UserFormDto;
import com.hands.springdemo01.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser() {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setEmail("test@email.com");
        userFormDto.setName("hong gil dong");
        userFormDto.setAddress("Seoul Korea");
        userFormDto.setPassword("1234");

        return User.createUser(userFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("user_join")
    public void saveUserTest() {
        User user = createUser();
        User savedUser = userService.saveUser(user);

        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getAddress(), savedUser.getAddress());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getRole(), savedUser.getRole());
    }

    @Test
    @DisplayName("user_duplicated_join")
    public void saveDuplicateUserTest() {
        User user1 = createUser();
        User user2 = createUser();

        userService.saveUser(user1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            userService.saveUser(user2);
        });

        assertEquals("already_existing_email", e.getMessage());

    }
}