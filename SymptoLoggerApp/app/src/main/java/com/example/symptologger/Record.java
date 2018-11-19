package com.example.symptologger;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Date;

class Record {
    //private String comment;
    private Date date;
    private Location location;
    private String title;

    public Record(){
        //this.comment = "";
        this.date = new Date();
    }

    public Record(/*String comment,*/ Date date, String title) {
      //  this.comment = comment;
        this.date = date;
        this.title = title;
    }

    //public String getComment() {
      //  return this.comment;
    //}

    //public void setComment(String comment) {
      //  this.comment = comment;
    //}

    public String getTitle(){
        return this.title;
    }

    public String toString(){
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addPhoto(){
        //When photo functionality established
    }

    public ArrayList<Photograph> getPhoto() {
        //When photo functionality established

        return new ArrayList<>();
    }

    public void addGeoLocation(){
        //When location services established and enabled
    }

    public Location getGeoLocation() {
        //When location services is up

        return this.location;
    }

    public void addCareProviderComment(){
        //When CareProviderComment is ready
    }

    public ArrayList<CareProviderComment> getCareProviderComment() {
        //When CareProviderComment is ready
        return new ArrayList<>();
    }

    public void addPatientComment(){
        //When patient comment is ready
    }

    public ArrayList<PatientComment> getPatientComment() {
        //When patient comment is ready
        return new ArrayList<>();
    }
}
