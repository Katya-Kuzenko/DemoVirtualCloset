package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password) {
        User user = new User(username, password);
    }
}
