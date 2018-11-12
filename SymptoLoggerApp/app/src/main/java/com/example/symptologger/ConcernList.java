package com.example.symptologger;

import java.util.ArrayList;
import java.util.Collection;

class ConcernList {

    private ArrayList<Concern> myConcerns;

    ConcernList(){
        this.myConcerns = new ArrayList<Concern>();
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
    }

    public int findCount() {
        return this.myConcerns.size();
    }
}
