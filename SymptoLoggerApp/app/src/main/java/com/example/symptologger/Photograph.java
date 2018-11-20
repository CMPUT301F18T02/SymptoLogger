package com.example.symptologger;

import android.media.Image;
import android.net.Uri;

import java.util.Date;

/**
 * Photograph represents the photo objects that can be added to a record.
 *
 * @author Jason Lee
 * @see BodyModel
 */

//https://stackoverflow.com/questions/29208007/what-is-the-data-type-for-images-in-java

public class Photograph {
    private Uri uri;
    private Date date;

    /**
     * Empty constructor
     */

    Photograph(){

    }

    /**
     * Constructor for photograph object, enables setting the image.
     * @param image the uri for the image
     */

    Photograph(Uri image){
        this.date = new Date();
        this.uri = image;
    }

    /**
     * Another constructor, enabling setting the image as well as the date.
     * @param image the uri for the image
     * @param date the date
     */

    Photograph(Uri image, Date date){
        this.date = date;
        this.uri = image;
    }

    /**
     * Sets the uri
     * @param uri the uri for the image
     */

    public void setURL(Uri uri) {
        this.uri = uri;
    }

    /**
     * Gets the uri for the image
     * @return uri
     */

    public Uri getURL(){
        return this.uri;
    }

    /**
     * Gets the date associated with the image
     * @return
     */

    public Date getDate() {
        return this.date;
    }

    /**
     * Sets the date for a particular image.
     * @param date the date
     */

    public void setDate(Date date) {
        this.date = date;
    }

}

