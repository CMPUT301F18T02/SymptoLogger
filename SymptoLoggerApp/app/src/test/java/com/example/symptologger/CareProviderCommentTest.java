package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CareProviderCommentTest {


    @Test
    public void testGetUser(){
        User user = new User();
        String problem = "Problem";
        CareProviderComment comment = new CareProviderComment(user, problem);

        User testuser = comment.getUser();

        assertEquals(user, testuser);
    }

    @Test
    public void testGetDate(){
        Date date = new Date();
        String problem = "Problem";
        CareProviderComment comment = new CareProviderComment(null, problem,date);

        Date testDate = comment.getDate();

        assertEquals(date, testDate);
    }

    @Test
    public void testSetDate(){
        CareProviderComment comment = new CareProviderComment(null, "Problem2");

        Date myDate = new Date();

        comment.setDate(myDate);

        Date testDate = comment.getDate();

        assertEquals(myDate, testDate);
    }


    @Test
    public void testgetComment(){
        String comment = "Problem3";
        CareProviderComment cpc = new CareProviderComment(null, comment);

        String testComment = cpc.getComment();

        assertEquals(comment, testComment);
    }

    @Test
    public void testSetComment(){
        CareProviderComment cpc = new CareProviderComment(null, "");

        String comment = "Problem4";

        cpc.setComment(comment);

        String testcomment = cpc.getComment();

        assertEquals(comment, testcomment);
    }

}
