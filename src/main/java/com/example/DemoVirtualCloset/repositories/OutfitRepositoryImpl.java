package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.Outfit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OutfitRepositoryImpl extends AbstractFileRepository<UUID, Outfit> implements OutfitRepository {
    public static final String OUTFIT_FILE_NAME = "outfit.json";
    private final String outfitFilesPath;

    public OutfitRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.outfit}") String outfitFilesPath) {
        super(objectMapper);
        this.outfitFilesPath = outfitFilesPath;
    }

    @Override
    public Outfit save(Outfit entity) {
        File file = getOutfitFile(entity);
        if (!file.exists()) {
            createFile(file);
            writeAllValues(file, List.of(entity));
        } else {
            List<Outfit> outfits = readAllValues(file, Outfit.class);
            outfits.add(entity);
            writeAllValues(file, outfits);
        }
        return entity;
    }

    @Override
    public Optional<Outfit> findById(UUID uuid) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public void deleteById(UUID uuid) {
        throw new RuntimeException("Method not supported");
    }

    @Override
    public List<Outfit> findAllByUserUuid(UUID userUuid) {
        return readAllValues(getOutfitFile(userUuid), Outfit.class);
    }

    private File getOutfitFile(Outfit entity) {
        return getOutfitFile(entity.getUserUuid());
    }

    private File getOutfitFile(UUID userUuid) {
        return new File(String.join(File.separator, outfitFilesPath, userUuid.toString(), OUTFIT_FILE_NAME));
    }
}