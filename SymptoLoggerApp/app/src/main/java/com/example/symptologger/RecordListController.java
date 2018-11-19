package com.example.symptologger;

public class RecordListController {
    private static RecordList recordList = null;

    static public RecordList getRecordList(){
        if (recordList == null){
            recordList = new RecordList();
        }
        return recordList;
    }

    public void addRecord(Record record){
        getRecordList().addRecord(record);
    }

    public boolean containsRecord(Record record){
        return getRecordList().containsRecord(record);
    }

    public void deleteRecord(Record record){
        getRecordList().deleteRecord(record);
    }

    public int findCount(){
        return getRecordList().findCount();
    }
}
