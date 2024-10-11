package com.example.users.models;

public class NewUser {
    //  Your users should store the following information:
    //  A username
    //  A first name
    //  A last name
    //  An email
    //  A phone number
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public NewUser() {}

    public NewUser(String username, String firstName, String lastName, String email, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public NewUser(NewUser newUser) {
        username = newUser.getUsername();
        firstName = newUser.getFirstName();
        lastName = newUser.getLastName();
        email = newUser.getEmail();
        phoneNumber = newUser.getPhoneNumber();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
