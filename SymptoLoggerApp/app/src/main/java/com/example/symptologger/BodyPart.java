package com.example.symptologger;

import java.util.ArrayList;

public class BodyPart {
    private int bodyPartId;
    private String body;

    public String getBody() {
        return this.body;
    }

    public BodyPart(int bodyPartId, String body) {
        this.bodyPartId = bodyPartId;
        this.body = body;
    }

}
