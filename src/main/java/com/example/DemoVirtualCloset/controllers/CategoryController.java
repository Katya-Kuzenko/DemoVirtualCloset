package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/create-category")
    public String showCreateCategoryForm(CategoryDto categoryDto) {
        return "newCategoryForm";
    }

    @PostMapping("/create")
    public String createCategory(@Valid CategoryDto categoryDto) {
        categoryService.save(categoryDto.getName());
        return "redirect:/category-view";
    }
}
