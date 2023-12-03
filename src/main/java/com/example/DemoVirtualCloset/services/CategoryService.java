package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}