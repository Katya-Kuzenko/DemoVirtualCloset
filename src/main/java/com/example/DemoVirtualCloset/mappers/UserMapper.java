package com.example.DemoVirtualCloset.mappers;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.dto.RegisterUserDto;
import com.example.DemoVirtualCloset.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return new UserDto(user.getUuid(), user.getName());
    }

    public User toUser(RegisterUserDto registerUserDto) {
        return new User(registerUserDto.getName(), registerUserDto.getPassword());
    }
}