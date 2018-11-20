package com.example.symptologger;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Date;


/**
 * The record class. A record can contain photos and other details pertaining to a concern.
 *
 * @author Patrick Tamm
 * @see Concern, RecordList,
 */

class Record {
    //private String comment;
    private Date date;
    private Location location;
    private String title;

    /**
     * The first of two constructors for the Record class. If no title or
     * date are supplied, this constructor is used.
     */

    public Record(){
        this.title = "";
        this.date = new Date();
    }

    /**
     * optional title and date are supplied to constructor.
     * @param date the date value for the record
     * @param title the title given to the record
     */

    public Record(Date date, String title) {
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

    /**
     * returns the title of the record
     * @return title
     */

    public String getTitle(){
        return this.title;
    }

    /**
     * used when displaying records, such as in a ListView.
     * @return title
     */

    public String toString(){
        return this.title;
    }

    /**
     * gets the date of the record
     * @return date
     */

    public Date getDate() {
        return this.date;
    }

    /**
     * sets the date for a previously created record
     * @param date the new date value
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * adding photos to records.
     */

    public void addPhoto(){
        //When photo functionality established
    }

    /**
     * gets the photos associated with a record.
     * @return list of photos
     */

    public ArrayList<Photograph> getPhotos() {
        //When photo functionality established

        return new ArrayList<>();
    }


    /**
     * adding a geolocation to a record
     */

    public void addGeoLocation(){
        //When location services established and enabled
    }

    /**
     * getting a geolocation from a record.
     * @return geolocation
     */

    public Location getGeoLocation() {
        //When location services is up

        return this.location;
    }

    /**
     * adding comments made by care providers assigned to patient.
     */

    public void addCareProviderComment(){
        //When CareProviderComment is ready
    }

    /**
     * getting comments made by care providers assigned to patient
     * @return care provider comments
     */

    public ArrayList<CareProviderComment> getCareProviderComment() {
        //When CareProviderComment is ready
        return new ArrayList<>();
    }

    /**
     * adding a patient comment
     */

    public void addPatientComment(){
        //When patient comment is ready
    }

    /**
     * returns the comments made by patients
     * @return patient comments
     */

    public ArrayList<PatientComment> getPatientComment() {
        //When patient comment is ready
        return new ArrayList<>();
    }
}
