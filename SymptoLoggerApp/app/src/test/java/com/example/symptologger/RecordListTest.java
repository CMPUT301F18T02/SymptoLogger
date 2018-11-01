package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecordListTest {

    @Test
    public void testAddRecord(){
        RecordList recordList = new RecordList();

        Record record = new Record();

        recordList.addRecord(record);

        assertTrue(recordList.containsRecord(record));
    }

    @Test
    public void testDeleteRecord(){
        RecordList recordList = new RecordList();

        Record record = new Record();

        recordList.addRecord(record);

        assertTrue(recordList.containsRecord(record));

        recordList.deleteRecord(record);

        assertFalse(recordList.containsRecord(record));
    }

    @Test
    public void testRecordCount(){
        RecordList recordList = new RecordList();

        int num = 10;

        for (int i=1;i<=num;i++){
            recordList.addRecord(new Record());
        }

        int total = recordList.findCount();

        assertEquals(num,total);
    }

}
