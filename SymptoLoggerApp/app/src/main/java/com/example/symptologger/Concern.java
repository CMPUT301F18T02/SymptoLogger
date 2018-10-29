package com.example.symptologger;

import java.util.Date;

class Concern {
    private String title;
    private Date date;
    private String description;
    private RecordList myRecords;

    Concern(){
        this.title = "No title given";
        this.date = new Date();
        this.description = "No description given";
        this.myRecords = new RecordList();
    }

    Concern(String title, Date date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void addPhoto(){
        //When photo functionality established
    }

    public void addGeoLocation(){
        //When location services established and enabled
    }

    //A general "record" until we have the specifics of photo, geo-location and comments ironed out.
    public void addRecord(Record record) {
        this.myRecords.addRecord(record);
    }

    public boolean recordListContains(Record record) {
        return this.myRecords.containsRecord(record);
    }

    public void removeRecord(Record record) {
        this.myRecords.deleteRecord(record);
    }
}
