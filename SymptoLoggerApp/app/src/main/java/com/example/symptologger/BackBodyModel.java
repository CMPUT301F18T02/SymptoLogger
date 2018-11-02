package com.example.symptologger;

import java.util.ArrayList;

public class BackBodyModel extends BodyModel {
    private Photograph bodyFrame;
    private User user;
    private ArrayList<Integer> bodyPartsId;

    private ArrayList<Integer> backBodyPartsId;

    public BackBodyModel(User user, int partId){
        super(user,partId);
    }
}
