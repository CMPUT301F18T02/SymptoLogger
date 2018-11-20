package com.example.symptologger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import io.searchbox.annotations.JestId;

/**
 * The Concern class. Models a medical concern that a patient has.
 * @author Patrick Tamm
 * @see ConcernList, Record
 */

class Concern {
    private String title;
    private Date date;
    private String description;
    private RecordList myRecords;

    @JestId
    private String id;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Concern class constructor. There are three, as the patient has the option of passing a title,
     * description and date. This constructor takes no parameters. All constructors initialize title,
     * date, description and myRecords, an object of type RecordList.
     * @see RecordList
     */

    Concern(){
        this.title = "No title given";
        this.date = new Date();
        this.description = "No description given";
        this.myRecords = new RecordList();
    }

    /**
     * A secondary constructor, giving the patient the option of setting a title, description and date.
     * @param title the title for the concern
     * @param date the date associated with a particular concern
     * @param description details about the concern
     * @throws TitleTooLongException if the title exceeds 30 characters
     * @throws DescriptionTooLongException if the description exceeds 300 characters
     */

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

    /**
     * A tertiary constructor for the Concern class. Gives the user the option of setting title and
     * description. The date is automatically set to the current date.
     *
     * @param title the title for the concern
     * @param description the date associated with a particular concern
     * @throws TitleTooLongException title exceeds 30 chars
     * @throws DescriptionTooLongException description exceeds 300 chars
     */

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

    /**
     * This is what determines what is displayed when a Concern object is shown, for example,
     * in a ListView.
     * @return concatenation of title, record count and date
     */

    public String toString(){
        return getTitle()+"\t\t\t\t\t"+findRecordCount()+"\n"+getDate();
    }


    /**
     * setTitle allows setting of the private title variable. It enables patients to modify the title
     * of a concern.
     * @param title title of concern
     * @throws TitleTooLongException title exceeds 30 chars
     */

    public void setTitle(String title) throws TitleTooLongException{
        if (title.length() <= 30){
            this.title = title;
        } else{
            throw new TitleTooLongException();
        }
    }

    /**
     * gets the title of the concern.
     * @return title
     */

    public String getTitle() {
        return this.title;
    }

    /**
     * gets the date value stored with the concern.
     *
     * @return date
     */

    public Date getDate() {
        return this.date;
    }

    /**
     * enables patients to modify the date value associated with a previously entered concern.
     * @param date date of concern
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * gets the concern's description.
     * @return description
     */

    public String getDescription() {
        return this.description;
    }


    /**
     * allows patients to modify a description for a previously entered concern.
     * @param description details about concern
     * @throws DescriptionTooLongException description exceeds 300 chars
     */

    public void setDescription(String description) throws DescriptionTooLongException{
        if (description.length() <= 300){
            this.description = description;
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
        return this.myRecords.findCount();
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
}
