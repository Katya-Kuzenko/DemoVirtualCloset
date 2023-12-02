package com.example.DemoVirtualCloset.repositories;


import com.example.DemoVirtualCloset.config.DatabaseFilePathProperties;
import com.example.DemoVirtualCloset.domain.Outfit;
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
public class OutfitRepositoryTest {

    @Autowired
    private OutfitRepository outfitRepository;

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
        Outfit outfit = new Outfit("name", UUID.randomUUID(), List.of(UUID.randomUUID(), UUID.randomUUID()));
        outfitRepository.save(outfit);

        File file = getOutfitFile(outfit.getUserUuid());
        assertTrue(file.exists());

        List<Outfit> outfits = objectMapper.readerForListOf(Outfit.class).readValue(file);
        Optional<Outfit> outfitOpt = outfits.stream().findFirst();
        assertTrue(outfitOpt.isPresent());
        Outfit savedOutfit = outfitOpt.get();

        assertEquals(outfit.getUuid(), savedOutfit.getUuid());
        assertEquals(outfit.getName(), savedOutfit.getName());
        assertEquals(outfit.getUserUuid(), savedOutfit.getUserUuid());
        assertIterableEquals(outfit.getClosingItemUuids(), savedOutfit.getClosingItemUuids());
    }

    @Test
    void findAllByUserUuidNotExistTest() {
        List<Outfit> outfits = outfitRepository.findAllByUserUuid(UUID.randomUUID());
        assertTrue(outfits.isEmpty());
    }

    @Test
    void findAllByUserUuidTest() throws IOException {
        Outfit outfit1 = new Outfit("name", UUID.randomUUID(), List.of(UUID.randomUUID(), UUID.randomUUID()));
        Outfit outfit2 = new Outfit("name", outfit1.getUserUuid(), List.of(UUID.randomUUID(), UUID.randomUUID()));

        File outfitFile = getOutfitFile(outfit1.getUserUuid());
        createFile(outfitFile);

        objectMapper.writeValue(outfitFile, List.of(outfit1, outfit2));

        List<Outfit> outfits = outfitRepository.findAllByUserUuid(outfit1.getUserUuid());
        assertEquals(2, outfits.size());
    }

    private void createFile(File file) throws IOException {
        Files.createDirectories(file.getParentFile().toPath());
        file.createNewFile();
    }

    private File getOutfitFile(UUID userUuid) {
        return new File(String.join(File.separator, databaseFilePathProperties.getOutfitPath(), userUuid.toString(), OutfitRepositoryImpl.OUTFIT_FILE_NAME));
    }
}