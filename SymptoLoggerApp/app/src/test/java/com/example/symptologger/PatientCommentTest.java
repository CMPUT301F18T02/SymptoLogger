package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PatientCommentTest {

    @Test
    public void testGetUser(){
        User user = new User();
        String problem = "Problem";
        PatientComment comment = new PatientComment(user, problem);

        User testuser = comment.getUser();

        assertEquals(user, testuser);
    }

    @Test
    public void testGetDate(){
        Date date = new Date();
        String problem = "Problem";
        PatientComment comment = new PatientComment(null, problem,date);

        Date testDate = comment.getDate();

        assertEquals(date, testDate);
    }

    @Test
    public void testSetDate(){
        PatientComment comment = new PatientComment(null, "Problem2");

        Date myDate = new Date();

        comment.setDate(myDate);

        Date testDate = comment.getDate();

        assertEquals(myDate, testDate);
    }


    @Test
    public void testgetComment(){
        String comment = "Problem3";
        PatientComment pc = new PatientComment(null, comment);

        String testComment = pc.getComment();

        assertEquals(comment, testComment);
    }

    @Test
    public void testSetComment(){
        PatientComment pc = new PatientComment(null, "");

        String comment = "Problem4";

        pc.setComment(comment);

        String testcomment = pc.getComment();

        assertEquals(comment, testcomment);
    }
}