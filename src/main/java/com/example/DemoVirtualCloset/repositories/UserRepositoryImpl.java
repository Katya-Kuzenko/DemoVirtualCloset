package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.exceptions.UserExistException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Repository
@Component
@Service
public class UserRepositoryImpl implements UserRepository {

    public static final String JSON_EXTENSION = ".json";
    private final String userFilesPath;
    private final ObjectMapper objectMapper;

    public UserRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.users}") String userFilesPath) {
        this.objectMapper = objectMapper;
        this.userFilesPath = userFilesPath;
    }

    @Override
    public User save(User entity) {
        File file = getUserFile(entity);
        boolean exist = createFile(file);
        if (exist) {
            throw new UserExistException("User exist with name " + entity.getName());
        }
        writeValue(file, entity);
        return entity;
    }

    private boolean createFile(File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeValue(File file, User user) {
        try {
            objectMapper.writeValue(file, user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(UUID uuid) {
        File usersDirectory = new File(userFilesPath);
        File[] userFiles = usersDirectory.listFiles();
        Optional<User> user = Optional.empty();
        if (userFiles != null) {
            user = Arrays.stream(userFiles)
                    .map(this::readValue)
                    .filter(el -> el.getUuid().equals(uuid))
                    .findFirst();
        }

        return user;
    }

    private User readValue(File file) {
        try {
            return objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(UUID uuid) {
        findById(uuid).ifPresent(user -> getUserFile(user).delete());
    }

    @Override
    public void deleteAll() {
        File usersDirectory = new File(userFilesPath);
        File[] userFiles = usersDirectory.listFiles();
        if (userFiles != null) {
            for (File userFile : userFiles) {
                userFile.delete();
            }
        }
    }

    private File getUserFile(User entity) {
        return new File(String.join(userFilesPath, entity.getName(), File.pathSeparator) + JSON_EXTENSION);
    }
}