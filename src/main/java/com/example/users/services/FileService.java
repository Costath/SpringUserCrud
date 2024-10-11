package com.example.users.services;

import com.example.users.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {
    ObjectMapper objectMapper = new ObjectMapper();

    public List<User> ReadUsersFromJsonFile(String fileName) throws IOException {
        File file = new ClassPathResource(fileName).getFile();
        String usersStr = new String(Files.readAllBytes(file.toPath()));
        return Arrays.asList(objectMapper.readValue(usersStr, User[].class));
    }

    public void WriteToJsonFile(List<User> users, String fileName) {
        try {
            File file = new ClassPathResource(fileName).getFile();
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
