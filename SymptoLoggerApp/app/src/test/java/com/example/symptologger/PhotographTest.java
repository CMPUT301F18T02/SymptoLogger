package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PhotographTest {

    @Test
    public void testGetDate(){
        Date date = new Date();
        String problem = "Problem";
        Photograph photo = new Photograph(date,problem);

        Date testDate = photo.getDate();

        assertEquals(date, testDate);
    }

    @Test
    public void testSetDate(){
        Photograph comment = new Photograph(null, "Problem2");

        Date myDate = new Date();

        comment.setDate(myDate);

        Date testDate = comment.getDate();

        assertEquals(myDate, testDate);
    }


    @Test
    public void testgetURL(){
        String url = "http://example.ca";
        Photograph photo = new Photograph(null, url);

        String testurl = photo.getURL();

        assertEquals(url, testurl);
    }

    @Test
    public void testSetURL(){
        Photograph photo = new Photograph(null, "");

        String url = "http://example.ca";

        photo.setURL(url);

        String testurl = photo.getURL();

        assertEquals(url, testurl);
    }

}
