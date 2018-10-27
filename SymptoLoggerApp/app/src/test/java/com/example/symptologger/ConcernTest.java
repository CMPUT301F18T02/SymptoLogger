package com.example.symptologger;

public class ConcernTest {

    public String testGetTitle(){
        Concern testConcern = new Concern();

        return testConcern.getTitle();
    }

    public void testSetTitle(String title){

        Concern testConcern = new Concern();

        testConcern.setTitle(title);
    }
}
