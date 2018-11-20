package com.example.symptologger;

import java.util.ArrayList;

/**
 * FrontBodyModel extends BodyModel, and represents the patient from the front.
 *
 * @author Jason Lee
 * @see BodyModel
 */

public class FrontBodyModel extends BodyModel {
    private Photograph bodyFrame;
    private User user;
    private ArrayList<Integer> bodyPartsId;

    private ArrayList<Integer> frontBodyPartsId;

    public FrontBodyModel(User user, int partId){
        super(user,partId);
    }
}