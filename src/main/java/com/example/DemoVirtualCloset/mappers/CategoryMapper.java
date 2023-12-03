package com.example.DemoVirtualCloset.mappers;

import com.example.DemoVirtualCloset.domain.Category;
import com.example.DemoVirtualCloset.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category) {
        return new CategoryDto(category.getUuid(), category.getName());
    }
}