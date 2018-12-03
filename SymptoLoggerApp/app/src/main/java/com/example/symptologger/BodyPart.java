package com.example.symptologger;

import java.util.ArrayList;

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
