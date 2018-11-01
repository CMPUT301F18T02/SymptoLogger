package com.example.symptologger;

import android.media.Image;

import java.util.Date;

//https://stackoverflow.com/questions/29208007/what-is-the-data-type-for-images-in-java

public class Photograph {
    private String url;
    private Date date;


    Photograph(Date date, String image){
        setDate(date);
        setURL(image);
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getURL(){
        return this.url;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

