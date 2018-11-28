package com.example.symptologger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
 * The Concern class. Models a medical concern that a patient has.
 * @author Patrick Tamm
 * @see ConcernList, Record
 */

class Concern {
    private String concernTitle;
    private Date concernDate;
    private String concernDescription;
    private RecordList myRecords;
    private String userName;

    @JestId
    private String id;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Concern class constructor. There are three, as the patient has the option of passing a title,
     * description and date. This constructor takes no parameters. All constructors initialize title,
     * date, description and myRecords, an object of type RecordList.
     * @see RecordList
     */

    Concern(String userName){
        this.concernTitle = "No title given";
        this.concernDate = new Date();
        this.concernDescription = "No description given";
        this.myRecords = new RecordList();
        this.userName = userName;
    }

    /**
     * A secondary constructor, giving the patient the option of setting a title, description and date.
     * @param title the title for the concern
     * @param date the date associated with a particular concern
     * @param description details about the concern
     * @throws TitleTooLongException if the title exceeds 30 characters
     * @throws DescriptionTooLongException if the description exceeds 300 characters
     */

    Concern(String title, Date date, String description, String userName) throws TitleTooLongException, DescriptionTooLongException{
        if (title.length() <= 30){
            this.concernTitle = title;
        } else {
            throw new TitleTooLongException();
        }
        this.concernDate = date;
        if (description.length()<= 300){
            this.concernDescription = description;
        } else{
            throw new DescriptionTooLongException();
        }
        this.myRecords = new RecordList();
        this.userName = userName;
    }

    /**
     * A tertiary constructor for the Concern class. Gives the user the option of setting title and
     * description. The date is automatically set to the current date.
     *
     * @param title the title for the concern
     * @param description the date associated with a particular concern
     * @throws TitleTooLongException title exceeds 30 chars
     * @throws DescriptionTooLongException description exceeds 300 chars
     */

    Concern(String title, String description, String userName) throws TitleTooLongException, DescriptionTooLongException {
        if (title.length() > 30){
            throw new TitleTooLongException();
        } else {
            this.concernTitle = title;
        }
        this.concernDate = new Date();
        if (description.length() <= 300){
            this.concernDescription = description;
        } else {
            throw new DescriptionTooLongException();
        }
        this.myRecords = new RecordList();
        this.userName = userName;
    }

    /**
     * This is what determines what is displayed when a Concern object is shown, for example,
     * in a ListView.
     * @return concatenation of title, record count and date
     */

    public String toString(){
        return this.concernTitle+"\t\t\t\t\t"+findRecordCount()+"\n"+this.concernDate;
    }


    /**
     * setTitle allows setting of the private title variable. It enables patients to modify the title
     * of a concern.
     * @param title title of concern
     * @throws TitleTooLongException title exceeds 30 chars
     */

    public void setTitle(String title) throws TitleTooLongException{
        if (title.length() <= 30){
            this.concernTitle = title;
        } else{
            throw new TitleTooLongException();
        }
    }

    /**
     * gets the title of the concern.
     * @return title
     */

    public String getTitle() {
        return this.concernTitle;
    }

    /**
     * gets the date value stored with the concern.
     *
     * @return date
     */

    public Date getDate() {
        return this.concernDate;
    }

    /**
     * enables patients to modify the date value associated with a previously entered concern.
     * @param date date of concern
     */

    public void setDate(Date date) {
        this.concernDate = date;
    }

    /**
     * gets the concern's description.
     * @return description
     */

    public String getDescription() {
        return this.concernDescription;
    }


    /**
     * allows patients to modify a description for a previously entered concern.
     * @param description details about concern
     * @throws DescriptionTooLongException description exceeds 300 chars
     */

    public void setDescription(String description) throws DescriptionTooLongException{
        if (description.length() <= 300){
            this.concernDescription = description;
        } else {
            throw new DescriptionTooLongException();
        }
    }

    /**
     * addRecord enables adding records to the concern's record list.
     * @param record a new record
     */

    public void addRecord(Record record) {
        this.myRecords.addRecord(record);
    }

    /**
     * returns the concern's record list.
     * @return myRecords
     */

    public Collection<Record> getRecords(){
        return this.myRecords.getRecords();
    }

    /**
     * determines if a particular record is contained in the concern's record list.
     * @param record the record the caller wants to check
     * @return boolean value: true if the record is found in the list, false otherwise.
     */

    public boolean recordListContains(Record record) {
        return this.myRecords.containsRecord(record);
    }

    /**
     * removes a record from the concern's record list.
     * @param record a record to be deleted
     */

    public void removeRecord(Record record) {
        this.myRecords.deleteRecord(record);
    }

    /**
     * determines the number of records contained in the concern's record list.
     * @return int value for the number of records
     */

    public int findRecordCount() {
        //return this.myRecords.findCount();
        return 0;
    }

    /** The below getter/setter methods are for the id generated by elastic search.
     * @param id generated by elastic search
     */

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUserName(){
        return this.userName;
    }
}
