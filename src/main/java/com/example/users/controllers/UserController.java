package com.example.users.controllers;

import com.example.users.models.NewUser;
import com.example.users.models.User;
import com.example.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //• POST /users - create a new user
    @PostMapping()
    ResponseEntity<User> createUser(@RequestBody NewUser newUser) {
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(newUser);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //• GET /users - get a list of users
    @GetMapping()
    ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //• GET /users/{id}
    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //• PUT /users/{id}
    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User existingUser = userService.findUserById(id);

        if (existingUser == null || existingUser.getId() != user.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean success = userService.updateUser(user);

        if (!success) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    //• DELETE /users/{id}
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable int id) {
        User user = userService.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!userService.deleteUser(user)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
