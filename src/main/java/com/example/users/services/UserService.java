package com.example.users.services;

import com.example.users.models.NewUser;
import com.example.users.models.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final String file = "data/users.json";
    List<User> Users = new ArrayList<>();
    Integer NextUserId;
    FileService fileService = new FileService();

    public UserService() {
        try {
            Users.addAll(fileService.ReadUsersFromJsonFile(file));

            Optional<User> lastUserOpt = Users.stream().max(Comparator.comparingInt(User::getId));
            NextUserId = lastUserOpt.map(User::getId).orElse(-1) + 1;

        } catch (IOException ex) {
            ex.printStackTrace();
            Users = new ArrayList<>();
            NextUserId = 0;
        }
    }

    //• Creating new users
    public User createUser(NewUser newUser) {
        User user = new User(NextUserId++, newUser);

        try {
            Users.add(user);
            fileService.WriteToJsonFile(Users, file);
        } catch (Exception ex) {
            ex.printStackTrace();
            NextUserId --;
        }

        return user;
    }


    //• Listing a single user
    public User findUserById(int id) {
        return Users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    //• Listing all available users
    public List<User> getUsers() {
        return Users;
    }

    //• Updating an existing user
    public boolean updateUser(User user) {
        Users.remove(user);

        return Users.add(user);
    }

    //• Deleting an existing user
    public boolean deleteUser(User user) {
        boolean success = Users.remove(user);

        if (success) {
            fileService.WriteToJsonFile(Users, file);
        }

        return success;
    }
}
