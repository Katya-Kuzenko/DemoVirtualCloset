package com.example.DemoVirtualCloset.controllers;

import com.example.DemoVirtualCloset.dto.CategoryDtoForView;
import com.example.DemoVirtualCloset.services.CategoryDtoForViewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryDtoController {

    private CategoryDtoForViewService categoryDtoForViewService;

    @Autowired
    public CategoryDtoController(CategoryDtoForViewService categoryDtoForViewService) {
        this.categoryDtoForViewService = categoryDtoForViewService;
    }

    @GetMapping("/create-category")
    public String showCreateCategoryForm(CategoryDtoForView categoryDtoForView) {
        return "newCategoryForm";
    }

    @PostMapping("/category")
    public String createCategory(@Valid CategoryDtoForView categoryDtoForView) {
        categoryDtoForViewService.create(categoryDtoForView);
        return"redirect:/category-view";
    }

}
