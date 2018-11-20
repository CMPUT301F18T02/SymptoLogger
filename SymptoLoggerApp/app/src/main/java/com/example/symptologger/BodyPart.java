package com.example.symptologger;

import java.util.ArrayList;

/**
 * The Body part class is intended to represent a body part on a "paper-doll" model image of a
 * patient. The model will be used to represent where on the body a photo has been taken when the
 * patient wishes to keep track of symptoms.
 *
 * @author Remi Arshad
 * @see Photograph, BodyModel
 */


public class BodyPart {
    private int bodyPartId;
    private String body;

    /**
     * The constructor for a body part object.
     * @param bodyPartId the id of the body part, used to determine which body part is being represented.
     * @param body the "body" containing the body part.
     */

    public BodyPart(int bodyPartId, String body) {
        this.bodyPartId = bodyPartId;
        this.body = body;
    }

    /**
     * Get body as string
     * @return body
     */

    public String getBody() {
        return this.body;
    }


}
