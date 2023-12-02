package com.example.DemoVirtualCloset.repositories;


import com.example.DemoVirtualCloset.config.DatabaseFilePathProperties;
import com.example.DemoVirtualCloset.domain.User;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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
        User user = new User("name", "passwrd");
        userRepository.save(user);

        File userFile = getUserFile(user);
        assertTrue(userFile.exists());

        User savedUser = objectMapper.readValue(userFile, User.class);
        assertEquals(user.getUuid(), savedUser.getUuid());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    void findByIdTest() throws IOException {
        User user = new User("testFind", "testFind");
        File userFile = getUserFile(user);
        userFile.createNewFile();
        objectMapper.writeValue(userFile, user);

        Optional<User> result = userRepository.findById(user.getUuid());
        assertTrue(result.isPresent());
        User resultUser = result.get();
        assertEquals(user.getUuid(), resultUser.getUuid());
        assertEquals(user.getName(), resultUser.getName());
        assertEquals(user.getPassword(), resultUser.getPassword());
    }

    @Test
    void deleteByIdTest() throws IOException {
        User user = new User("testDelete", "testDelete");
        File userFile = getUserFile(user);
        userFile.createNewFile();
        objectMapper.writeValue(getUserFile(user), user);

        userRepository.deleteById(user.getUuid());

        assertFalse(userFile.exists());
    }

    private File getUserFile(User entity) {
        return new File(String.join(File.separator, databaseFilePathProperties.getUsersPath(), entity.getName()) + ".json");
    }
}