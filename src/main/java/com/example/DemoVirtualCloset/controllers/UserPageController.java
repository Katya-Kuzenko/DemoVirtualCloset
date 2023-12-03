package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.dto.CategoryDtoForView;
import com.example.DemoVirtualCloset.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {

    private UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails user) {
        ModelAndView modelAndView = new ModelAndView("userpage");

        userService.createUser(user.getUsername(), user.getPassword());

        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/category-view")
    public ModelAndView index(@AuthenticationPrincipal CategoryDtoForView categoryDtoForView) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryDtoForView);
        return modelAndView;
    }
}
