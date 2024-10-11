package com.example.users.models;

import java.util.Objects;

public class User extends NewUser {
    private int id;

    public User() {
        super();
    }

    public User(int id, String username, String firstName, String lastName, String email, String phoneNumber) {
        super(username, firstName, lastName, email, phoneNumber);
        this.id = id;
    }

    public User(int id, NewUser newUser) {
        super(newUser);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
