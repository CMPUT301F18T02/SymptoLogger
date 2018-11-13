package com.example.symptologger;

public class User {
    private String firstName;
    private String lastName;
    private String id;
    private String email;
    private String cell;


    public User() {}

    public User(String id, String firstName, String lastName, String email, String cell) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cell = cell;
    }

    public void setFirstName(String first) {}

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String last) {}

    public String getLastName() {
        return this.lastName;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCell() {
        return this.cell;
    }

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
