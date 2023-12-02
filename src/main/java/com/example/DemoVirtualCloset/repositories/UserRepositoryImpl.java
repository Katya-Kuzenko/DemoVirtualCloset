package com.example.DemoVirtualCloset.repositories;

import com.example.DemoVirtualCloset.domain.User;
import com.example.DemoVirtualCloset.exceptions.UserExistException;
import com.example.DemoVirtualCloset.exceptions.UserNotFoundException;
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

    private String filePath;
    private final ObjectMapper objectMapper;

    public UserRepositoryImpl(ObjectMapper objectMapper, @Value("${database.files.path.users}") String filePath) {
        this.objectMapper = objectMapper;
        this.filePath = filePath;
    }

    @Override
    public User save(User entity) {
        File file = new File(String.join(filePath, entity.getName(), "/"));
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
    public User findById(UUID uuid) {
        File usersDirectory = new File(filePath);
        File[] userFiles = usersDirectory.listFiles();
        Optional<User> user = Optional.empty();
        if (userFiles != null) {
            user = Arrays.stream(userFiles)
                    .map(this::readValue)
                    .filter(el -> el.getUuid().equals(uuid))
                    .findFirst();
        }

        return user.orElseThrow(() -> new UserNotFoundException("User not found by uuid " + uuid));
    }

    private User readValue(File file) {
        try {
            return objectMapper.readValue(file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}