//package com.example.DemoVirtualCloset.controllers;
//
//import com.example.DemoVirtualCloset.services.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class CategoryController {
//
//    private CategoryService categoryService;
//
//    @Autowired
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping("/category-view")
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("categories", categoryService.findAll());
//        return modelAndView;
//    }
//}
