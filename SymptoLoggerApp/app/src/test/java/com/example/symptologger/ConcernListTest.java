package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConcernListTest {

    @Test
    public void testAddConcern(){

        ConcernList concernList = new ConcernList("11111111");

        Concern concern = new Concern("11111111");

        concernList.addConcern(concern,"11111111");

        assertTrue(concernList.containsConcern(concern));
    }

    @Test
    public void testDeleteConcern(){
        ConcernList concernList = new ConcernList("11111111");

        Concern concern = new Concern("11111111");

        concernList.addConcern(concern,"11111111");
        assertTrue(concernList.containsConcern(concern));

        concernList.deleteConcern(concern);

        assertFalse(concernList.containsConcern(concern));
    }

    @Test
    public void testConcernCount(){
        ConcernList concernList = new ConcernList("11111111");

        int num = 10;

        for (int i=1;i<=num;i++){
            concernList.addConcern(new Concern("11111111"),"11111111");
        }

        int total = concernList.findCount();

        assertEquals(num,total);
    }
}
