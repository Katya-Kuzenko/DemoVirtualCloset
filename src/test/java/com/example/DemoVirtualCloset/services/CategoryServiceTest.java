package com.example.DemoVirtualCloset.services;

import com.example.DemoVirtualCloset.domain.Category;
import com.example.DemoVirtualCloset.dto.CategoryDto;
import com.example.DemoVirtualCloset.mappers.CategoryMapper;
import com.example.DemoVirtualCloset.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void init() {
        categoryService = new CategoryServiceImpl(categoryRepository, new CategoryMapper());
    }

    @Test
    public void getAllTest() {
        List<Category> categories = List.of(new Category("test1"), new Category("test2"));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> result = categoryService.findAll();
        for (int i = 0; i < result.size(); i++) {
            Category category = categories.get(i);
            CategoryDto categoryDto = result.get(i);
            assertEquals(category.getUuid(), categoryDto.getUuid());
            assertEquals(category.getName(), categoryDto.getName());
        }
    }
}