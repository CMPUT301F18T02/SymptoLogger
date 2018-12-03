package com.example.symptologger;


import android.location.Location;

import com.google.android.gms.maps.GoogleMap;


import java.util.ArrayList;
import java.util.Date;

import io.searchbox.annotations.JestId;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


/**
 * The record class. A record can contain photos and other details pertaining to a concern.
 *
 * @author Patrick Tamm
 * @see Concern, RecordList,
 */

class Record {
    //private String comment;
    private String date;
    private Location location;
    private String title;
    private String concernTitle;
    private String userName;

    private ArrayList<Photograph> photo = new ArrayList<Photograph>();
    private ArrayList<BodyPart> views = new ArrayList<BodyPart>();

    @JestId
    private String id;

    /**
     * The first of two constructors for the Record class. If no title or
     * date are supplied, this constructor is used.
     */


    public Record(){
        this.title = "";
        this.date = new Date().toString();
        this.concernTitle = "";
        this.userName = "";
    }

    /**
     * optional title and date are supplied to constructor.
     * @param date the date value for the record
     * @param title the title given to the record
     */

    public Record(Date date, String title, String userName, String concernTitle) {
      //  this.comment = comment;
        this.date = date.toString();
        this.title = title;
        this.userName = userName;
        this.concernTitle = concernTitle;
    }

    public Record(String date, String title, String userName, String concernTitle) {
        //  this.comment = comment;
        this.date = date;
        this.title = title;
        this.userName = userName;
        this.concernTitle = concernTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void addView(BodyPart bp){
        views.add(bp);
    }

    public void removeView(Integer index){
        views.remove(index);
    }


    public String getDate() {
        return this.date;
    }

    /**
     * sets the date for a previously created record
     * @param date the new date value
     */

    public void setDate(Date date) {
        this.date = date.toString();
    }

    /**
     * Overloaded setDate; it can either take a date value or string
     * @param date String representation of date
     */

    public void setDate(String date){
        this.date = date;
    }

    public String getConcernTitle(){
        return this.concernTitle;
    }

    public String getUserName(){
        return this.userName;
    }

    /**
     * adding photos to records.
     */


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
