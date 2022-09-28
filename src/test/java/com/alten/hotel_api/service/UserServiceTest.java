package com.alten.hotel_api.service;

import com.alten.hotel_api.model.User;
import com.alten.hotel_api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getUserWithExistingElement_ShouldReturnUser() {

        // given
        Long existingUserId = 1L;
        User expectedUser = new User();
        expectedUser.setId(existingUserId);
        expectedUser.setCreatedAt(LocalDateTime.now());

        Optional<User> opt = Optional.of(expectedUser);

        // when
        when(userRepository.findById(anyLong())).thenReturn(opt);
        User returnedValue = userService.getUser(existingUserId);

        //then
        assertThat(returnedValue).isInstanceOf(User.class);
        assertThat(returnedValue.getId()).isEqualTo(existingUserId);
    }
}

