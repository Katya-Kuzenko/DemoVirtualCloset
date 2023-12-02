package com.example.DemoVirtualCloset.repositories;


import com.example.DemoVirtualCloset.config.DatabaseFilePathProperties;
import com.example.DemoVirtualCloset.domain.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DatabaseFilePathProperties databaseFilePathProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() throws IOException {
        for (String path : databaseFilePathProperties.getPath().values()) {
            Files.createDirectories(Path.of(path));
        }
    }

    @AfterEach
    void clean() throws IOException {
        for (String path : databaseFilePathProperties.getPath().values()) {
            FileSystemUtils.deleteRecursively(Path.of(path));
        }
    }

    @Test
    void saveTest() throws IOException {
        Category category = new Category("category name");
        categoryRepository.save(category);

        File file = getCategoryFile();
        assertTrue(file.exists());

        objectMapper.readerForListOf(Category.class).readValue(file);
        List<Category> categories = objectMapper.readerForListOf(Category.class).readValue(file);
        Optional<Category> savedCategoryOpt = categories.stream().findFirst();
        assertTrue(savedCategoryOpt.isPresent());
        Category savedCategory = savedCategoryOpt.get();

        assertEquals(category.getUuid(), savedCategory.getUuid());
        assertEquals(category.getName(), savedCategory.getName());
    }

    @Test
    void findByIdNotExistTest() {
        Optional<Category> result = categoryRepository.findById(UUID.randomUUID());
        assertFalse(result.isPresent());
    }

    @Test
    void findByIdTest() throws IOException {
        Category category = new Category("test");
        File cateforyFile = getCategoryFile();
        cateforyFile.createNewFile();
        objectMapper.writeValue(cateforyFile, List.of(category));

        Optional<Category> result = categoryRepository.findById(category.getUuid());
        assertTrue(result.isPresent());
        Category resultCategory = result.get();
        assertEquals(category.getUuid(), resultCategory.getUuid());
        assertEquals(category.getName(), resultCategory.getName());
    }

    @Test
    void deleteByIdTest() throws IOException {
        Category category = new Category("test");
        File file = getCategoryFile();
        file.createNewFile();
        objectMapper.writeValue(file, List.of(category));

        categoryRepository.deleteById(category.getUuid());
        List<Category> categories = objectMapper.readerForListOf(Category.class).readValue(file);
        assertTrue(categories.isEmpty());
    }

    @Test
    void findAllTest() throws IOException {
        Category category1 = new Category("test1");
        Category category2 = new Category("test2");
        File cateforyFile = getCategoryFile();
        cateforyFile.createNewFile();
        objectMapper.writeValue(cateforyFile, List.of(category1, category2));

        List<Category> categories = categoryRepository.findAll();
        assertEquals(2, categories.size());
    }

    private File getCategoryFile() {
        return new File(String.join(File.separator, databaseFilePathProperties.getCategoriesPath(), CategoryRepositoryImpl.CATEGORY_FILE_NAME));
    }
}