package com.example.symptologger;

import android.net.Uri;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PhotographTest {

    @Test
    public void testGetDate(){
        Date date = new Date();
        Photograph photo = new Photograph(null, date);

        Date testDate = photo.getDate();

        assertEquals(date, testDate);
    }

    @Test
    public void testSetDate(){
        Photograph comment = new Photograph(null, new Date());

        Date myDate = new Date();

        comment.setDate(myDate);

        Date testDate = comment.getDate();

        assertEquals(myDate, testDate);
    }


//    @Test
//    public void testgetURL(){
//        Uri url = http://example.com;
//        Photograph photo = new Photograph(url, new Date());
//
//        Uri testurl = photo.getURL();
//
//        assertEquals(url, testurl);
//    }

//    @Test
//    public void testSetURL(){
//        Photograph photo = new Photograph(null, new Date());
//
//        Uri url = http://example.ca;
//
//        photo.setURL(url);
//
//        Uri testurl = photo.getURL();
//
//        assertEquals(url, testurl);
//    }

}
