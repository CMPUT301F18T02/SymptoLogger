package com.example.symptologger;

import java.util.ArrayList;

class RecordList {

    private ArrayList<Record> myRecords;

    RecordList(){
        this.myRecords = new ArrayList<Record>();
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
