package com.example.symptologger;

import java.util.ArrayList;

/**
 * Controller for list of users.
 */

public class UserList {
    private ArrayList<User> userList = new ArrayList<User>();

    /**
     * Constructor
     */
    public UserList() {
        this.userList = new ArrayList<>();
    }

    /**
     * Add a user to the list
     * @param user a User object
     */
    public void addUser(User user) {
        this.userList.add(user);
    }

    /**
     * Remove a User
     * @param user User to be removed
     */
    public void removeUser(User user) {
        this.userList.remove(user);
    }

    /**
     * Get the list of users
     * @return Users in an array list
     */
    public ArrayList<User> getUserList() {
        return this.userList;
    }

    /**
     * Get number of users in the list
     * @return size of user list
     */
    public int getUserCount() {
        return this.userList.size();
    }

    /**
     * Get the user chosen on a app interface by position
     * @param pos position of the screen
     * @return selected user
     */
    public User getUserByPos(int pos) {
        return new User();
    }
}
