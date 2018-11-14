package com.example.jjlee3.testapp;

import android.media.Image;
import android.net.Uri;

import java.net.URI;
import java.util.Date;

//https://stackoverflow.com/questions/29208007/what-is-the-data-type-for-images-in-java

public class Photograph {
    private Uri uri;
    private Date date;


    Photograph(Uri image){
        Date date = new Date();
        setDate(date);
        setURL(image);
    }

    public void setURL(Uri uri) {
        this.uri = uri;
    }

    public Uri getURL(){
        return this.uri;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}


