package com.example.symptologger;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ExecutionException;

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
 * The ConcernList model class represents a list of concerns. It allows for a patient to have his or
 * her own set of concerns.
 *
 * @author Patrick Tamm
 * @see Concern
 */

class ConcernList {

    private ArrayList<Concern> myConcerns;
    private ArrayList<ConcernListener> concernListeners;


    /**
     * The constructor for the ConcernList. Creates a new ArrayList of type Concern and an ArrayList
     * of ConcernListeners.
     */

    //TODO add check for server connectivity
    ConcernList(String userName){
        ElasticSearchClient.GetConcerns getConcerns = new ElasticSearchClient.GetConcerns();
        getConcerns.execute(userName);
        try {
            this.myConcerns = getConcerns.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.concernListeners = new ArrayList<ConcernListener>();
    }

    ConcernList(){
        this.myConcerns = new ArrayList<Concern>();
    }

    /**
     * returns the list of concerns
     * @return myConcerns a list of concerns
     */

    public Collection<Concern> getConcernsList(){
        return this.myConcerns;
    }

    /**
     * allows the adding of concerns to the concern list.
     * @param concern a new concern to be added to the list.
     */

    public void addConcern(Concern concern, String userName) {
        ElasticSearchClient.AddConcern addConcern = new ElasticSearchClient.AddConcern();
        String title = concern.getTitle();
        String des = concern.getDescription();
        String date = concern.getDate();
        String user = userName;
        addConcern.execute(title, date, des, user);
        this.myConcerns.add(concern);
        notifyListeners();
    }


    public void addConcern(Concern concern){
        this.myConcerns.add(concern);
    }

    /**
     * determines if a particular concern is contained in the list.
     * @param concern the concern to be found
     * @return true/false
     */

    public boolean containsConcern(Concern concern) {
        return this.myConcerns.contains(concern);
    }

    /**
     * removes a concern from the list.
     * @param concern the concern to be deleted
     */

    public void deleteConcern(Concern concern) {
        this.myConcerns.remove(concern);
        ElasticSearchClient.DeleteConcern esDeleteConcern = new ElasticSearchClient.DeleteConcern();
        esDeleteConcern.execute(concern.getTitle(),concern.getUserName());
        notifyListeners();
    }

    /**
     * determines the number of concerns found in the list.
     * @return size of concern list
     */

    public int findCount() {
        return this.myConcerns.size();
    }

    /**
     * informs the listeners that a change has been made.
     */

    public void notifyListeners(){
        for (ConcernListener cl : concernListeners){
            cl.updateListener();
        }
    }

    /**
     * adds a new listener to the list of listeners.
     * @param cl "concern listener", the particular listener to be added.
     */

    public void addListener(ConcernListener cl){
        this.concernListeners.add(cl);
        this.concernListeners.size();
    }
}
