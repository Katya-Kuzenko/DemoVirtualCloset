package com.example.DemoVirtualCloset.controllers;
import com.example.DemoVirtualCloset.domain.Category;
import com.example.DemoVirtualCloset.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/create-category")
    public String showCreateCategoryForm(Category category) {
        return "newCategoryForm";
    }

    @PostMapping("/category")
    public String createCategory(@Valid Category category) {
        categoryService.create(category);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/";
    }

}
