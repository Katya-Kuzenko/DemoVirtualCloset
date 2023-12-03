package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.dto.RegisterUserDto;
import com.example.DemoVirtualCloset.dto.UserDto;
import com.example.DemoVirtualCloset.mappers.UserMapper;
import com.example.DemoVirtualCloset.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private UserService userService;

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl(userRepository, new UserMapper());
    }

    @Test
    public void registerUserTest() {
        RegisterUserDto registerUserDto = new RegisterUserDto("name", "pass");

        UserDto result = userService.registerUser(registerUserDto);

        verify(userRepository).save(userArgumentCaptor.capture());
        User user = userArgumentCaptor.getValue();
        assertEquals(registerUserDto.getName(), user.getName());
        assertEquals(registerUserDto.getPassword(), user.getPassword());

        assertEquals(user.getUuid(), result.getUuid());
        assertEquals(user.getName(), result.getName());
    }
}