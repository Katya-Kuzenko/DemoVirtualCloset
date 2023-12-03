package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.dto.RegisterUserDto;
import com.example.DemoVirtualCloset.dto.UserDto;

public interface UserService {
    UserDto registerUser(RegisterUserDto registerUserDto);
}