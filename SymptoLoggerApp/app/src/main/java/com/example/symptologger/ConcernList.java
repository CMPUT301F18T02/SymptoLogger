package com.example.symptologger;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

class ConcernList {

    private ArrayList<Concern> myConcerns;
    private ArrayList<ConcernListener> concernListeners;
    private ElasticSearchConcernController.AddConcernsTask addConcernsTask
            = new ElasticSearchConcernController.AddConcernsTask();

    ConcernList(){
        this.myConcerns = new ArrayList<Concern>();
        this.concernListeners = new ArrayList<ConcernListener>();
        ElasticSearchConcernController.AddConcernsTask addConcernsTask =
                new ElasticSearchConcernController.AddConcernsTask();
    }


    public Collection<Concern> getConcerns(){
//        ElasticSearchConcernController.GetConcernsTask getConcernsTask =
//                new ElasticSearchConcernController.GetConcernsTask();
//        getConcernsTask.execute(""); //We're not searching for anything, just getting first result from elasticsearch
//        try {
//            this.myConcerns = getConcernsTask.get();
//        } catch (Exception e) {
//            Log.e("Error","Failed to get concerns out of async object.");
//        }
        return this.myConcerns;
    }

    public void addConcern(Concern concern) {
        this.myConcerns.add(concern);
        //addConcernsTask.execute(concern);
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
