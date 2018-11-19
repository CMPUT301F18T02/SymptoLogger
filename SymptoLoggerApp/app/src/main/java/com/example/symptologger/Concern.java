package com.example.symptologger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import io.searchbox.annotations.JestId;

class Concern {
    private String title;
    private Date date;
    private String description;
    private RecordList myRecords;

    @JestId
    private String id;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Concern(){
        this.title = "No title given";
        this.date = new Date();
        this.description = "No description given";
        this.myRecords = new RecordList();
    }

    Concern(String title, Date date, String description) throws TitleTooLongException, DescriptionTooLongException{
        if (title.length() <= 30){
            this.title = title;
        } else {
            throw new TitleTooLongException();
        }
        this.date = date;
        if (description.length()<= 300){
            this.description = description;
        } else{
            throw new DescriptionTooLongException();
        }
        this.myRecords = new RecordList();
    }

    Concern(String title, String description) throws TitleTooLongException, DescriptionTooLongException {
        if (title.length() > 30){
            throw new TitleTooLongException();
        } else {
            this.title = title;
        }
        this.date = new Date();
        if (description.length() <= 300){
            this.description = description;
        } else {
            throw new DescriptionTooLongException();
        }
        this.myRecords = new RecordList();
    }

    public String toString(){
        return getTitle()+"\t\t\t\t\t"+findRecordCount()+"\n"+getDate();
    }


    public void setTitle(String title) throws TitleTooLongException{
        if (title.length() <= 30){
            this.title = title;
        } else{
            throw new TitleTooLongException();
        }
    }

    public String getTitle() {
        return this.title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) throws DescriptionTooLongException{
        if (description.length() <= 300){
            this.description = description;
        } else {
            throw new DescriptionTooLongException();
        }
    }

    //The below may or may not be needed: have to wait to see how adding photos/geolocations will be handled.

    //A general "record" until we have the specifics of photo, geo-location and comments ironed out.
    public void addRecord(Record record) {
        this.myRecords.addRecord(record);
    }

    public Collection<Record> getRecords(){
        return this.myRecords.getRecords();
    }

    public boolean recordListContains(Record record) {
        return this.myRecords.containsRecord(record);
    }

    public void removeRecord(Record record) {
        this.myRecords.deleteRecord(record);
    }

    public int findRecordCount() {
        return this.myRecords.findCount();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
