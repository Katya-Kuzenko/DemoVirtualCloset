package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService categoryService) {
        this.userService = categoryService;
    }

    @GetMapping("/**")
    public ModelAndView index(@AuthenticationPrincipal UserDetails user) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", user);

        userService.createUser(user.getUsername(), user.getPassword());
        System.out.println(user.getUsername() + " " + user.getPassword());

        return modelAndView;
    }

}
