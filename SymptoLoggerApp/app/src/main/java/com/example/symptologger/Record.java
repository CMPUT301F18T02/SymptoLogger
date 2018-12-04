package com.example.symptologger;


import android.location.Location;

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

    /**
     * Another optional constructor.
     * @param date
     * @param title
     * @param userName
     * @param concernTitle
     */
    public Record(String date, String title, String userName, String concernTitle) {
        //  this.comment = comment;
        this.date = date;
        this.title = title;
        this.userName = userName;
        this.concernTitle = concernTitle;
    }


    /**
     * For elasticsearch
     * @param id from elasticsearch
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * For elasticsearch
     * @return id from elasticsearch
     */
    public String getId() {
        return id;
    }

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
     * Adds a view
     * @param bp body part
     */
    public void addView(BodyPart bp){
        views.add(bp);
    }

    /**
     * removeView takes an integer and removes the view
     * @param index the index to remove
     */
    public void removeView(Integer index){
        views.remove(index);
    }


    /**
     * gets the date of the record
     * @return date
     */
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

    /**
     * getConcernTitle gets the concern title associated with the record.
     * @return concernTitle: the title of the concern
     */
    public String getConcernTitle(){
        return this.concernTitle;
    }


    /**
     * getUserName returns the user name associated with the record.
     * @return userName
     */
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

    /**
     * returns the photos
     * @return photos
     */
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
