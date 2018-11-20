package com.example.symptologger;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

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

    ConcernList(){
        this.myConcerns = new ArrayList<Concern>();
        this.concernListeners = new ArrayList<ConcernListener>();
    }


    /**
     * returns the list of concerns
     * @return myConcerns a list of concerns
     */

    public Collection<Concern> getConcerns(){

        return this.myConcerns;
    }

    /**
     * allows the adding of concerns to the concern list.
     * @param concern a new concern to be added to the list.
     */

    public void addConcern(Concern concern) {
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
        concernListeners.add(cl);
    }
}
