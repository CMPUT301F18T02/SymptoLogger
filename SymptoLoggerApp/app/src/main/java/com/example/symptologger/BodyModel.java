package com.example.symptologger;

import java.util.ArrayList;
import java.util.Date;

/**
 * The body model class represents the model of the patient used to represent where on the body a photo
 * has been taken.
 *
 * @author Jason Lee
 * @see BackBodyModel, FrontBodyModel
 */

public class BodyModel {
    private Photograph BodyFrame;
    private User user;
    private ArrayList<Integer> bodyPartsId;


    /**
     * The constructor of the body model object
     * @param user which user is using the app
     * @param bodyPartId an id to be added to a collection of body parts
     */

    public BodyModel(User user, int bodyPartId){
        this.user = user;
        this.bodyPartsId = new ArrayList<>();
        this.bodyPartsId.add(bodyPartId);
    }

    /**
     * returns a photo that is mapped to a particular location
     * @param bodyPartId which body part the photo represents
     * @return the photo associated with the specified body part
     */

    public Photograph getPhoto(int bodyPartId){
        //String url = "";
        Photograph photo = new Photograph();
        return photo;
    }

    /**
     * Sets a photo to a body part location
     * @param bodyPartId which body part does the photo represent
     * @param photo the photo of the concern
     */

    public void setPhoto(int bodyPartId, Photograph photo){
        return;
    }

}
