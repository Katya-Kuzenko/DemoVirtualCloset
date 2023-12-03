package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
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
            writeAllValues(file, List.of(entity));
        } else {
            List<Category> categories = readAllValues(file, Category.class);
            categories.add(entity);
            writeAllValues(file, categories);
        }
        return entity;
    }

    @Override
    public Optional<Category> findById(UUID uuid) {
        return findAll()
                .stream()
                .filter(el -> el.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public void deleteById(UUID uuid) {
        File file = getCategoryFile();
        if (file.exists()) {
            List<Category> categories = readAllValues(file, Category.class);
            categories.removeIf(el -> el.getUuid().equals(uuid));
            writeAllValues(file, categories);
        }
    }

    @Override
    public List<Category> findAll() {
        return readAllValues(getCategoryFile(), Category.class);
    }

    private File getCategoryFile() {
        return new File(String.join(File.separator, categoryFilesPath, CATEGORY_FILE_NAME));
    }
}