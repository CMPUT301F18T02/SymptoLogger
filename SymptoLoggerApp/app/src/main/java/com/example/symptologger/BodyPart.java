package com.example.symptologger;

import java.util.ArrayList;

public class BodyPart {
    private int bodyPartId;
    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;

    public BodyPart(int bodyPartId, int xStart, int xEnd, int yStart, int yEnd){
        this.bodyPartId = bodyPartId;
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.yEnd = yEnd;
    }

    public ArrayList<Integer> getRange(int bodyPartId){
        ArrayList<Integer> coords = new ArrayList<>();
        return coords;
    }

}
