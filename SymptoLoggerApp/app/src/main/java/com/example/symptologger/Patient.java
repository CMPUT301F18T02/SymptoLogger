package com.example.symptologger;

import java.util.ArrayList;

import io.searchbox.annotations.JestId;

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
 * <p>
 *     Patient model. Extends User class.
 *
 * </p>
 */
public class Patient extends User {
    private ArrayList<Concern> concerns;
    private CareProviderList careProviderList;
    private String cpUserName;

    @JestId
    private String id;

    /**
     * Constructor of Patient class.
     * @param userID id of the patient
     * @param email email address
     * @param cell cell number in string
     * @param user_type type of user
     */
    public Patient(String userID, String email, String cell, String user_type) {
        super(userID, email, cell, user_type);

        this.concerns = new ArrayList<>();
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getCpUserName(){
        return this.cpUserName;
    }

    public void setCpUserName(String cpUserName){
        this.cpUserName = cpUserName;
    }

//    public void setUserID(String userPrompt) {
//        try {
//            super.setUserID(userPrompt);
//        } catch (UserIDTooShortException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Get patient's concern
     * @return list of Concerns
     */
    public ArrayList<Concern> getConcerns() {
        return this.concerns;
    }

    /**
     * Add a concern
     * @param concern a concern object
     */
    public void addConcern(Concern concern) {
        this.concerns.add(concern);

    }

    /**
     * Remove a concern from the list
     * @param concern
     */
    public void deleteConcern(Concern concern) {
        this.concerns.remove(concern);
    }
}
