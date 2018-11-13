package com.example.symptologger;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList;

    public UserList() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user) {}

    public void removeUser(User user) {}

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public User getUserByPos(int pos) {
        return new User();
    }
}
