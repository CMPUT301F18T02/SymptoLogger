package com.example.jjlee3.testapp;

import android.net.Uri;

import java.io.Serializable;
import java.util.Date;

public class Photograph {
    private Uri uri;
    private Date date;

    Photograph(){

    }

    Photograph(Uri image){
        this.date = new Date();
        this.uri = image;
    }

    Photograph(Uri image, Date date){
        this.date = date;
        this.uri = image;
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


