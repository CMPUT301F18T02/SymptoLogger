package com.example.symptologger;

import java.util.ArrayList;
import java.util.Collection;

class RecordList {

    private ArrayList<Record> myRecords;

    RecordList(){
        this.myRecords = new ArrayList<Record>();
    }

    public Collection<Record> getRecords(){
        return this.myRecords;
    }

    public void addRecord(Record record) {
        this.myRecords.add(record);
    }


    public boolean containsRecord(Record record) {
        return this.myRecords.contains(record);
    }

    public void deleteRecord(Record record) {
        this.myRecords.remove(record);
    }

    public int findCount() {
        return this.myRecords.size();
    }
}
