package com.example.jjlee3.bptest;

import android.location.Location;
import android.widget.TextView;

import java.util.ArrayList;

public class BodyPart {
    private int bodyPartId;
    private String body;

    public String getBody(){
        return this.body;
    }

    public BodyPart(int bodyPartId, String body){
        this.bodyPartId = bodyPartId;
        this.body = body;
        //this.x = (int)x;
        //this.y = (int)y;
    }
    //list concern activity

}
