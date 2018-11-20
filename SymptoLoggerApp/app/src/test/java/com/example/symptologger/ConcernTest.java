package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ConcernTest {

    @Test // The "@Test" came from https://developer.android.com/training/testing/unit-testing/local-unit-tests#java, 2018-10-27
    public void testGetTitle(){
        String test = "Problem";
        Concern testConcern = null;
        try {
            testConcern = new Concern(test, new Date(), null);
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

        Concern testConcern = new Concern();

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
        String problem = "Problem";
        Concern newConcern = null;
        try {
            newConcern = new Concern(problem, date, null);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        Date testDate = newConcern.getDate();

        assertEquals(date, testDate);
    }

    @Test
    public void testSetDate(){
        Concern myConcern = null;
        try {
            myConcern = new Concern("Problem2",null, null);
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        } catch (DescriptionTooLongException e) {
            e.printStackTrace();
        }

        Date myDate = new Date();

        myConcern.setDate(myDate);

        Date testDate = myConcern.getDate();

        assertEquals(testDate,myDate);
    }

    @Test
    public void testGetDescription(){
        String description = "My description";
        Concern concern = null;
        try {
            concern = new Concern("abc",new Date(), description);
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

        Concern concern = new Concern();

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

    //Right now, just testing general record class

    @Test
    public void testAddRecord(){
        Concern myConcern = new Concern();

        Record record = new Record();

        myConcern.addRecord(record);

        assertTrue(myConcern.recordListContains(record));

    }

    @Test
    public void testRemoveRecord(){

        Concern myConcern = new Concern();

        Record record = new Record();

        myConcern.addRecord(record);

        assertTrue(myConcern.recordListContains(record));

        myConcern.removeRecord(record);

        assertFalse(myConcern.recordListContains(record));

    }

    @Test
    public void testRecordCount(){
        Concern myConcern = new Concern();

        int num = 10;

        for (int i=1;i<=num;i++){
            myConcern.addRecord(new Record());
        }

        int total = myConcern.findRecordCount();

        assertEquals(total,num);
    }
}