package com.example.DemoVirtualCloset.repositories;


import com.example.DemoVirtualCloset.config.DatabaseFilePathProperties;
import com.example.DemoVirtualCloset.domain.ClosingItem;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class ClosingItemRepositoryTest {

    @Autowired
    private ClosingItemRepository closingItemRepository;

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
        ClosingItem closingItem = new ClosingItem("name", UUID.randomUUID(), UUID.randomUUID(), "image.jpg");
        closingItemRepository.save(closingItem);

        File file = getClosingItemFile(closingItem.getUserUuid());
        assertTrue(file.exists());

        List<ClosingItem> closingItems = objectMapper.readerForListOf(ClosingItem.class).readValue(file);
        Optional<ClosingItem> closingItemOpt = closingItems.stream().findFirst();
        assertTrue(closingItemOpt.isPresent());
        ClosingItem savedClosingItem = closingItemOpt.get();

        assertEquals(closingItem.getUuid(), savedClosingItem.getUuid());
        assertEquals(closingItem.getName(), savedClosingItem.getName());
        assertEquals(closingItem.getCategoryUuid(), savedClosingItem.getCategoryUuid());
        assertEquals(closingItem.getUserUuid(), savedClosingItem.getUserUuid());
        assertEquals(closingItem.getImageName(), savedClosingItem.getImageName());
    }

    @Test
    void findAllByUserUuidNotExistTest() {
        List<ClosingItem> closingItems = closingItemRepository.findAllByUserUuid(UUID.randomUUID());
        assertTrue(closingItems.isEmpty());
    }

    @Test
    void findAllByUserUuidTest() throws IOException {
        ClosingItem closingItem1 = new ClosingItem("name", UUID.randomUUID(), UUID.randomUUID(), "image.jpg");
        ClosingItem closingItem2 = new ClosingItem("name2", UUID.randomUUID(), closingItem1.getUserUuid(), "image2.jpg");

        File closingItemFile = getClosingItemFile(closingItem1.getUserUuid());
        createFile(closingItemFile);

        objectMapper.writeValue(closingItemFile, List.of(closingItem1, closingItem2));

        List<ClosingItem> closingItems = closingItemRepository.findAllByUserUuid(closingItem1.getUserUuid());
        assertEquals(2, closingItems.size());
    }

    private void createFile(File file) throws IOException {
        Files.createDirectories(file.getParentFile().toPath());
        file.createNewFile();
    }

    private File getClosingItemFile(UUID userUuid) {
        return new File(String.join(File.separator, databaseFilePathProperties.getClosetPath(), userUuid.toString(), ClosingItemRepositoryImpl.CLOSING_ITEMS_FILE_NAME));
    }
}