package com.example.symptologger;

import java.util.Collection;

public class UserList {
    private Collection<User> userList;

    public void addUser(User user) {}

    public void removeUser(User user) {}

    public Collection<User> getUserlist() {
        return userList;
    }

    public User getUserByPos(User user) {
        return user;
    }
}
