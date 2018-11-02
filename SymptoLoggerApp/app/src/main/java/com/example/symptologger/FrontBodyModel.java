package com.example.symptologger;

import java.util.ArrayList;

public class FrontBodyModel extends BodyModel {
    private Photograph bodyFrame;
    private User user;
    private ArrayList<Integer> bodyPartsId;

    private ArrayList<Integer> frontBodyPartsId;

    public FrontBodyModel(User user, int partId){
        super(user,partId);
    }
}