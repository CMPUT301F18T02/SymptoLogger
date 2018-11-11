package com.example.symptologger;

public class ConcernListController {
    private static ConcernList concernList = null;

    static public ConcernList getConcernList(){
        if (concernList == null){
            concernList = new ConcernList();
        }
        return concernList;
    }

    public void addConcern(Concern concern){
        getConcernList().addConcern(concern);
    }

    public boolean containsConcern(Concern concern){
        return getConcernList().containsConcern(concern);
    }

    public void deleteCocnern(Concern concern){
        getConcernList().deleteConcern(concern);
    }

    public int findCount(){
        return getConcernList().findCount();
    }
}
