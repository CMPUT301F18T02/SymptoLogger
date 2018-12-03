package com.example.symptologger;

import java.util.Date;

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
 * PatientComment represents the comments made by patients on their records. This is intended to
 * enable effective communication between care providers and patients.
 */
public class PatientComment implements Comment {
    private User user;
    private Date date;
    private String comment;

    /**
     * Constructor 1 for the patient comment
     * @param user the user adding the comment
     * @param comment the comment to be added
     */
    PatientComment(User user, String comment){
        this.comment = comment;
        //setUser(user);
        this.user = user;
        this.date = new Date();
    }

    /**
     * Constructor 2 for the patient comment. Allows the setting of the date value to a specified date.
     *
     * @param user the one adding a comment
     * @param comment the comment being added
     * @param date the date
     */
    PatientComment(User user, String comment, Date date){
        this.comment = comment;
        //setUser(user);
        this.user = user;
        this.date = date;
    }

    /**
     * Sets the date value
     * @param date the date
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Returns the date value
     * @return date
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * Returns the user who made the comment
     * @return user
     */
    public User getUser(){
        return this.user;
    }

    //public void setUser(User user){
    //    this.user = user;
    //}

    /**
     * Gets the comment
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * Sets the comment. Could be used for editing.
     * @param comment the comment being made
     */
    public void setComment(String comment){
        this.comment = comment;
    }

}
