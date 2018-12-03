package com.example.symptologger;

import android.media.Image;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

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
 *
 */

public class Photograph {
    private String photoID;
    private String encrypted;
    private String recordID;

    public ArrayList<String> bodyParts; //Instead of using BodyPart class I used String class since it only contains String anyways


    /**
     * Empty constructor
     */

    Photograph(){
        setID();
        this.encrypted = "";
        this.bodyParts = new ArrayList<>();
    }


    public String setID(){
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("MST"));
        date = new Date();
        photoID = formatter.format(date);
        return photoID;
    }

    /**
     * Gets the date associated with the image
     * @return
     */

    public String getID() {
        return this.photoID;
    }


    public void setEncrypted(String en){
        this.encrypted = en;
    }

    public String getEncrypted(){
        return encrypted;
    }

    public void addBodyLocation(String bp){
        bodyParts.add(bp);
    }

    public void removeBodyLocation(String bp){
        bodyParts.remove(bp);
    }

    public ArrayList<String> getBPs (){
        return bodyParts;
    }

    public String getRecordID(){
        return recordID;
    }
}

