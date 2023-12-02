package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.ClosingItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ClosingItemRepositoryImpl extends AbstractFileRepository<UUID, ClosingItem> implements ClosingItemRepository {
    public static final String CLOSING_ITEMS_FILE_NAME = "closing-items.json";
    private final String closetFilesPath;

    public ClosingItemRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.closet}") String userFilesPath) {
        super(objectMapper);
        this.closetFilesPath = userFilesPath;
    }

    @Override
    public ClosingItem save(ClosingItem entity) {
        File file = getClosingItemFile(entity);
        if (!file.exists()) {
            createFile(file);
            writeAllValues(file, List.of(entity));
        } else {
            List<ClosingItem> closingItems = readAllValues(file, ClosingItem.class);
            closingItems.add(entity);
            writeAllValues(file, closingItems);
        }
        return entity;
    }

    @Override
    public Optional<ClosingItem> findById(UUID uuid) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public void deleteById(UUID uuid) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public List<ClosingItem> findAllByUserUuid(UUID userUuid) {
        return readAllValues(getClosingItemFile(userUuid), ClosingItem.class);
    }

    private File getClosingItemFile(ClosingItem entity) {
        return getClosingItemFile(entity.getUserUuid());
    }

    private File getClosingItemFile(UUID userUuid) {
        return new File(String.join(File.separator, closetFilesPath, userUuid.toString(), CLOSING_ITEMS_FILE_NAME));
    }
}