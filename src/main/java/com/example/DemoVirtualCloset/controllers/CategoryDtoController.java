//package com.example.DemoVirtualCloset.controllers;
//
//import com.example.DemoVirtualCloset.services.CategoryService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class CategoryDtoController {

//    private CategoryService categoryService;
//
//    @Autowired
//    public UserController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping("/category-view")
//    public ModelAndView index(CategoryService categoryService) {
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("categories", categoryService);
//        return modelAndView;
//    }
//}
