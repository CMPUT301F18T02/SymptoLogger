package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConcernTest {

    @Test // The "@Test" came from https://developer.android.com/training/testing/unit-testing/local-unit-tests#java, 2018-10-27
    public void testGetTitle(){
        String test = "Problem";
        Concern testConcern = new Concern(test);

        String testTitle = testConcern.getTitle();

        assertEquals(testTitle,test);
    }

    @Test
    public void testSetTitle(){

        Concern testConcern = new Concern();

        String title = "Problem1";
        testConcern.setTitle(title);
        String testTitle = testConcern.getTitle();

        assertEquals(title, testTitle);
    }
}