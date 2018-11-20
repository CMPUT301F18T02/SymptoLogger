package com.example.symptologger;

import java.util.Date;

/**
 * The CareProviderComment class represents comments that care providers make on a patient's records.
 */

public class CareProviderComment implements Comment{
    private User user;
    private Date date;
    private String comment;

    /**
     * The first constructor for the care provider comment
     * @param user the username of the care provider making the comment
     * @param comment the comment being added
     */

    CareProviderComment(User user, String comment){
        this.comment = comment;
        //setUser(user);
        this.user = user;
        this.date = new Date();
    }

    /**
     * The second constructor for the care provider comment. Allows setting a particular date.
     * @param user the username of the care provider
     * @param comment the comment to be added
     * @param date the date
     */

    CareProviderComment(User user, String comment, Date date){
        this.comment = comment;
        this.user = user;
        //setUser(user);
        this.date = date;
    }

    /**
     * Sets the date for the comment
     * @param date the date for the comment
     */

    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Returns the date of the comment
     * @return date
     */

    public Date getDate(){
        return this.date;
    }

    /**
     * Returns the username of the commenter
     * @return user
     */

    public User getUser(){
        return this.user;
    }

    //public void setUser(User user){
    //    this.user = user;
    //}

    /**
     * Returns the comment
     * @return comment
     */

    public String getComment(){
        return this.comment;
    }

    /**
     * Sets the comment content
     * @param comment a string representing what the care provider wishes to say to the patient
     */

    public void setComment(String comment){
        this.comment = comment;
    }

}