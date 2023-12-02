package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryRepositoryImpl extends AbstractFileRepository<UUID, Category> implements CategoryRepository {
    public static final String CATEGORY_FILE_NAME = "categories.json";
    private final String categoryFilesPath;

    public CategoryRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.categories}") String categoryFilesPath) {
        super(objectMapper);
        this.categoryFilesPath = categoryFilesPath;
    }

    @Override
    public Category save(Category entity) {
        File file = getCategoryFile();
        if (!file.exists()) {
            createFile(file);
        }
        List<Category> categories = readAllValues(file);
        categories.add(entity);
        writeAllValues(file, categories);
        return entity;
    }

    @Override
    public Optional<Category> findById(UUID uuid) {
        return getAll()
                .stream()
                .filter(el -> el.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public void deleteById(UUID uuid) {
        File file = getCategoryFile();
        if (file.exists()) {
            List<Category> categories = readAllValues(file);
            categories.removeIf(el -> el.getUuid().equals(uuid));
            writeAllValues(file, categories);
        }
    }

    @Override
    public List<Category> getAll() {
        return readAllValues(getCategoryFile());
    }

    private File getCategoryFile() {
        return new File(String.join(File.separator, categoryFilesPath, CATEGORY_FILE_NAME));
    }
}