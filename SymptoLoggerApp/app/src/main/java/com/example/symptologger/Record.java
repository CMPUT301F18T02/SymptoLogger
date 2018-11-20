package com.example.symptologger;

import java.util.ArrayList;
import java.util.Date;

class Record {
    //private String comment;
    private Date date;
    private ArrayList<Photograph> photo = new ArrayList<Photograph>();
    private ArrayList<BodyPart> views = new ArrayList<BodyPart>();

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

    public void addView(BodyPart bp){
        views.add(bp);
    }

    public void removeView(Integer index){
        views.remove(index);
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addPhoto(Photograph photograph){

        if (photo.size() < 10) {
            photo.add(photograph);
        }
    }

    public ArrayList<Photograph> getPhoto(){
        return this.photo;
    }

    public void removePhoto(Integer index){
        //When photo functionality established
        photo.remove(index);
    }

    public void addGeoLocation(){
        //When location services established and enabled
    }

    public void addCareProviderComment(){
        //When CareProviderComment is ready
    }

    public void addPatientComment(){
        //When patient comment is ready
    }
}
