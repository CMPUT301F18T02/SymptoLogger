package com.example.symptologger;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Date;

class Record {
    //private String comment;
    private Date date;
    private GoogleMap location;

    public Record(){
        //this.comment = "";
        this.date = new Date();
    }

    public Record(String comment, Date date) {
      //  this.comment = comment;
        this.date = date;
    }

    //public String getComment() {
      //  return this.comment;
    //}

    //public void setComment(String comment) {
      //  this.comment = comment;
    //}

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

    public GoogleMap getGeoLocation() {
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
