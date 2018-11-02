package com.example.symptologger;

public class User {

    String id;
    String name;
    String email;
    String cell;

    public User(String id, String name, String email, String cell) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cell = cell;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }
}
