package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class RecordTest {

    /*@Test
    public void testGetComment(){
        String newComment = "I am a record comment";

        Record testRecord = new Record(newComment, new Date());

        String test = testRecord.getComment();

        assertEquals(newComment,test);
    }*/

    /*@Test
    public void testSetComment(){
        String newComment = "Testing set comment";

        Record record = new Record();

        assertNotEquals(newComment, record.getComment());

        record.setComment(newComment);

        assertEquals(newComment,record.getComment());
    }*/

    @Test
    public void testGetDate(){
        Date date = new Date();
        Record record = new Record();
        assertEquals(date.toString(),record.getDate());
    }

    @Test
    public void testSetDate() {
        Record myRecord = new Record();

        Date myDate = new Date();

        myRecord.setDate(myDate);

        String testDate = myRecord.getDate();

        assertEquals(testDate, myDate.toString());
    }

    @Test
    public void testGetUserName(){
        String user = "UNIT_TEST";
        Record record = new Record(new Date(), "UNIT_TESTING", user,"UNIT_TEST_CONCERN");

        String usr = record.getUserName();

        assertEquals(user,usr);
    }

    @Test
    public void testGetConcernTitle(){
        String concern = "UNIT_TEST";
        Record record = new Record(new Date(), "UNIT_TEST", "UNIT_TESTER",concern);

        String c = record.getConcernTitle();

        assertEquals(concern,c);
    }

}