package com.example.DemoVirtualCloset.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractFileRepository<ID, T> implements FileRepository<ID, T> {

    public static final String JSON_EXTENSION = ".json";

    private final ObjectMapper objectMapper;

    public AbstractFileRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeValue(File file, T entity) {
        try {
            objectMapper.writeValue(file, entity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void writeAllValues(File file, List<T> entities) {
        try {
            objectMapper.writeValue(file, entities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected T readValue(File file, Class<T> type) {
        try {
            return objectMapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<T> readAllValues(File file, Class<T> type) {
        try {
            if (!file.exists()) {
                return List.of();
            }
            return objectMapper.readerForListOf(type).readValue(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}