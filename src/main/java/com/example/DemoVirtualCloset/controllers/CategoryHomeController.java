package com.example.DemoVirtualCloset.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryHomeController {

//    private CategoryDtoService categoryDtoService;

//    @Autowired
//    public CategoryHomeController(CategoryDtoService categoryDtoService) {
//        this.categoryDtoService = categoryDtoService;
//    }

    @GetMapping("/category-view")
        public String showCategoryForm(){
//            ModelAndView modelAndView = new ModelAndView("categoryView");
//            modelAndView.addObject("categories", categoryDtoService.getAll());
//            return modelAndView;

            return "categoryView";
        }
}
