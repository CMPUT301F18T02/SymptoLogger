package com.example.symptologger;

import java.util.Date;

public class CareProviderComment implements Comment{
    private User user;
    private Date date;
    private String comment;

    CareProviderComment(User user, String comment){
        setComment(comment);
        //setUser(user);
        this.user = user;
        this.date = new Date();
    }

    CareProviderComment(User user, String comment, Date date){
        setComment(comment);
        this.user = user;
        //setUser(user);
        this.date = date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }

    public User getUser(){
        return this.user;
    }

    //public void setUser(User user){
    //    this.user = user;
    //}

    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

}