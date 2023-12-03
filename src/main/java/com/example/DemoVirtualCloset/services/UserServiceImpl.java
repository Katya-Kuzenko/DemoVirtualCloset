package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.dto.RegisterUserDto;
import com.example.DemoVirtualCloset.dto.UserDto;
import com.example.DemoVirtualCloset.mappers.UserMapper;
import com.example.DemoVirtualCloset.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto registerUser(RegisterUserDto registerUserDto) {
        User user = userMapper.toUser(registerUserDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }
}