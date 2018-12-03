package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class ConcernListTest {

    @Test
    public void testAddConcern(){

        ConcernList concernList = new ConcernList();
        Concern concern = null;
        try {
            concern = new Concern("P_UNITTEST","");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        concernList.addConcern(concern);

        assertTrue(concernList.containsConcern(concern));
    }

//    @Test
//    public void testDeleteConcern(){
//        ConcernList concernList = new ConcernList("P_UNITTEST2");
//
//        Concern concern = new Concern("111");
//
//        concernList.addConcern(concern,"P_UNITTEST");
//        assertTrue(concernList.containsConcern(concern));
//
//        concernList.deleteConcern(concern);
//
//        assertFalse(concernList.containsConcern(concern));
//    }

    @Test
    public void testConcernCount(){
        ConcernList concernList = new ConcernList();

        int num = 10;

        for (int i=1;i<=num;i++){
            try {
                concernList.addConcern(new Concern("P_UNITTEST3",""));
            } catch (TitleTooLongException e) {
                e.printStackTrace();
            }
        }

        int total = concernList.findCount();

        assertEquals(num,total);
    }
}
