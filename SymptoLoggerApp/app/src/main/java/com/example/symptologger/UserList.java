package com.example.symptologger;

import java.util.ArrayList;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * The list of users.
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
        return this.userList.get(pos);
    }
}
