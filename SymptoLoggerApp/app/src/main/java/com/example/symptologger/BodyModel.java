package com.example.symptologger;

import java.util.ArrayList;
import java.util.Date;

public class BodyModel {
    private Photograph BodyFrame;
    private User user;
    private ArrayList<Integer> bodyPartsId;

    public BodyModel(User user, int bodyPartId){
        this.user = user;
        this.bodyPartsId = new ArrayList<>();
        this.bodyPartsId.add(bodyPartId);
    }

    public Photograph getPhoto(int bodyPartId){
        String url = "";
        Date date = new Date();
        Photograph photo = new Photograph(date, url);
        return photo;

    }

    public void setPhoto(int bodyPartId, Photograph photo){
        return;
    }

}
