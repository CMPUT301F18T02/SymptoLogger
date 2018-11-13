package com.example.symptologger;

public class User {
    private String firstName;
    private String lastName;
    private String id;
    private String email;
    private String cell;
    private String user_type;

    public User() {}


    public User(String id, String firstName, String lastName, String email, String cell, String user_type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cell = cell;

        this.user_type = user_type;
    }

    public void setFirstName(String first) {}

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String last) {}

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public String getUsertype() { return user_type; }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
