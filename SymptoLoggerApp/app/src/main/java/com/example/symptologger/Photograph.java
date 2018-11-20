package com.example.symptologger;

import android.media.Image;
import android.net.Uri;

import java.util.Date;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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

