package com.example.symptologger;

import android.media.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

//https://stackoverflow.com/questions/29208007/what-is-the-data-type-for-images-in-java

public class Photograph {
    private String img;
    private Date date;


    Photograph(Date date, String image){
        setDate(date);
        setImage(image);
    }




    public void setImage(String image) {
        this.img = image;
    }

    public String getImage(){
        return this.img;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

