package com.example.symptologger;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

class ConcernList {

    private ArrayList<Concern> myConcerns;
    private ArrayList<ConcernListener> concernListeners;


    ConcernList(){
        this.myConcerns = new ArrayList<Concern>();
        this.concernListeners = new ArrayList<ConcernListener>();
    }


    public Collection<Concern> getConcerns(){

        return this.myConcerns;
    }

    public void addConcern(Concern concern) {
        this.myConcerns.add(concern);

    }

    public boolean containsConcern(Concern concern) {
        return this.myConcerns.contains(concern);
    }

    public void deleteConcern(Concern concern) {
        this.myConcerns.remove(concern);
        notifyListeners();
    }

    public int findCount() {
        return this.myConcerns.size();
    }

    public void notifyListeners(){
        for (ConcernListener cl : concernListeners){
            cl.updateListener();
        }
    }

    public void addListener(ConcernListener cl){
        concernListeners.add(cl);
    }
}
