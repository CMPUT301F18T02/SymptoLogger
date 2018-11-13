package com.example.symptologger;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> userList = new ArrayList<User>();

    public void addUser(User user) {userList.add(user);}

    public void removeUser(User user) {userList.remove(user);}

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User getUserByPos(int pos) {
        return new User();
    }
}
