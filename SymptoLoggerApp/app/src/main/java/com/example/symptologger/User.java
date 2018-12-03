package com.example.symptologger;

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
 * <p>
 *     User model.
 *     Contains getter and setter for id, first and last name, email and cell number.
 * </p>
 */

public class User {
    //private String firstName;
    //private String lastName;
    private String userID;
    private String email;
    private String cell;
    private String user_type;

    /**
     * Empty constructor.
     */
    public User() {}

    /**
     * Constructor with parameters
     * @param userID id of a user
     * @param email email address of the user
     * @param cell cell number of the user
     * @param user_type type of user
     */
    public User(String userID, String email, String cell, String user_type) {
        this.userID = userID;
      //  this.firstName = firstName;
        //this.lastName = lastName;
        this.email = email;
        this.cell = cell;
        this.user_type = user_type;
    }


    public String toString(){
        return this.userID;
    }

    /**
     * Get ID
     * @return ID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Get email
     * @return email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get cell number
     * @return cell number as string
     */
    public String getCell() {
        return this.cell;
    }

    /**
     * Set ID
     * @param userID
     */
    public void setUserID(String userID)throws UserIDTooShortException {
        if (userID.length() >= 8){
            this.userID = userID;
        } else {
            throw new UserIDTooShortException();
        }
    }

    /**
     * Set email
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set user type
     * @param user_type type of user
     */
    public void setUser_type(String user_type){
        this.user_type = user_type;
    }

    /**
     * Gets the type of the user
     * @return user_type
     */
    public String getUser_type() {
        return this.user_type;
    }

    /**
     * Set cell
     * @param cell north american phone number
     */
    public void setCell(String cell) {
        this.cell = cell;
    }
}
