package com.example.symptologger;

import java.util.ArrayList;

public class Patient extends User {
    private ArrayList<Concern> concerns;
    private CareProviderList careProviderList;

    public Patient(String id, String firstName, String lastName, String email, String cell) {
        super(id, firstName, lastName, email, cell);

        this.concerns = new ArrayList<>();
    }

    protected void createUserID(String userPrompt) {
//        if (userPrompt.length() < 8) {
//            throw new RuntimeException("User ID must be at least 8 characters.");
//        }
//        super.setUserID(userPrompt);
    }

    public ArrayList<Concern> getConcerns() {
        return this.concerns;
    }

    public void addConcern(Concern concern) {

    }

    public void updateConcern(Concern concern) {

    }

    public void deleteConcern(Concern concern) {

    }
}
