package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ConcernTest {

    @Test // The "@Test" came from https://developer.android.com/training/testing/unit-testing/local-unit-tests#java, 2018-10-27
    public void testGetTitle(){
        String test = "UNIT_TEST";
        Concern testConcern = null;
        try {
            testConcern = new Concern(test, new Date(), "","UNIT_TESTER");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        String testTitle = testConcern.getTitle();

        assertEquals(testTitle,test);
    }

    @Test
    public void testSetTitle(){

        Concern testConcern = null;
        try {
            testConcern = new Concern("UNIT_TESTER","");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        String title = "Problem1";
        try {
            testConcern.setTitle(title);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }
        String testTitle = testConcern.getTitle();

        assertEquals(title, testTitle);
    }


    @Test
    public void testGetDate(){

        Date date = new Date();
        String problem = "UNIT_TEST";
        Concern newConcern = null;
        try {
            newConcern = new Concern(problem, date, "","UNIT_TESTER");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        String testDate = newConcern.getDate();

        assertEquals(date.toString(), testDate);
    }

    @Test
    public void testSetDate(){
        Concern myConcern = null;
        try {
            myConcern = new Concern("UNIT_TEST","","UNIT_TESTER");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        String myDate = new Date().toString();

        myConcern.setDate(myDate);

        String testDate = myConcern.getDate();

        assertEquals(testDate,myDate);
    }

    @Test
    public void testGetDescription(){
        String description = "My description";
        Concern concern = null;
        try {
            concern = new Concern("UNIT_TEST",new Date(), description,"UNIT_TESTER");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        String d = concern.getDescription();

        assertEquals(description, d);
    }

    @Test
    public void testSetDescription(){
        String description = "A sample description";

        Concern concern = null;
        try {
            concern = new Concern("UNIT_TEST","");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        String des = concern.getDescription();

        assertNotEquals(des,description);

        try {
            concern.setDescription(description);
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        String newDes = concern.getDescription();

        assertEquals(description,newDes);
    }

    @Test
    public void testGetUserName(){
        Concern concern = null;
        String user = "UNIT_TEST";
        try {
            concern = new Concern(user,"");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        String usr = concern.getUserName();

        assertEquals(user,usr);
    }


//    @Test
//    public void testAddRecord(){
//        Concern myConcern = null;
//        try {
//            myConcern = new Concern("UNIT_TEST","");
//        } catch (TitleTooLongException e) {
//            e.printStackTrace();
//        }
//
//        Record record = new Record();
//
//        myConcern.addRecord(record);
//
//        assertTrue(myConcern.recordListContains(record));
//
//    }

//    @Test
//    public void testRemoveRecord() throws TitleTooLongException {
//
//        Concern myConcern = new Concern("UNIT_TEST","");
//
//        Record record = new Record();
//
//        myConcern.addRecord(record);
//
//        assertTrue(myConcern.recordListContains(record));
//
//        myConcern.removeRecord(record);
//
//        assertFalse(myConcern.recordListContains(record));
//
//    }

//    @Test
//    public void testRecordCount(){
//        Concern myConcern = null;
//        try {
//            myConcern = new Concern("UNIT_TEST","");
//        } catch (TitleTooLongException e) {
//            e.printStackTrace();
//        }
//
//        int num = 10;
//
//        for (int i=1;i<=num;i++){
//            myConcern.addRecord(new Record());
//        }
//
//        int total = myConcern.findRecordCount();
//
//        assertEquals(total,num);
//    }
}