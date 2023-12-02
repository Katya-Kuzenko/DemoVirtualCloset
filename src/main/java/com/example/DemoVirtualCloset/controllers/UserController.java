package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.services.CategoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/")
    public ModelAndView userPage(@AuthenticationPrincipal UserDetails user) {
        ModelAndView modelAndView = new ModelAndView("userpage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/category-view")
    public ModelAndView index(@AuthenticationPrincipal CategoryService categoryService) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService);
        return modelAndView;
    }
}
