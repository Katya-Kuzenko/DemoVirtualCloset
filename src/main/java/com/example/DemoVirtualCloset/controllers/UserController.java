package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.dto.RegisterUserDto;
import com.example.DemoVirtualCloset.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails user) {
        ModelAndView modelAndView = new ModelAndView("userpage");

        RegisterUserDto registerUserDto = new RegisterUserDto(user.getUsername(), user.getPassword());
        userService.registerUser(registerUserDto);

        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/category-view")
    public ModelAndView index(@AuthenticationPrincipal CategoryDto categoryDto) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryDto);
        return modelAndView;
    }
}
