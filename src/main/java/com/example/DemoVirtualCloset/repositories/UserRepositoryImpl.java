package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.exceptions.UserExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.File;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl extends AbstractFileRepository<UUID, User> implements UserRepository {
    private final String userFilesPath;

    public UserRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.users}") String userFilesPath) {
        super(objectMapper);
        this.userFilesPath = userFilesPath;
    }

    @Override
    public User save(User entity) {
        File file = getUserFile(entity);
        boolean created = createFile(file);
        if (!created) {
            throw new UserExistException("User exist with name " + entity.getName());
        }
        writeValue(file, entity);
        return entity;
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        File usersDirectory = new File(userFilesPath);
        File[] userFiles = usersDirectory.listFiles();
        Optional<User> user = Optional.empty();
        if (userFiles != null) {
            user = Arrays.stream(userFiles)
                    .map(file -> readValue(file, User.class))
                    .filter(el -> el.getUuid().equals(uuid))
                    .findFirst();
        }

        return user;
    }

    @Override
    public void deleteById(UUID uuid) {
        findById(uuid).ifPresent(user -> getUserFile(user).delete());
    }

    private File getUserFile(User entity) {
        return new File(String.join(File.separator, userFilesPath, entity.getName()) + JSON_EXTENSION);
    }
}