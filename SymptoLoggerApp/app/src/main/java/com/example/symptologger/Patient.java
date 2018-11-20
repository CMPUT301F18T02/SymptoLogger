package com.example.symptologger;

import java.util.ArrayList;

/**
 * <p>
 *     Patient model. Extends User class.
 *
 * </p>
 */
public class Patient extends User {
    private ArrayList<Concern> concerns;
    private CareProviderList careProviderList;

    /**
     * Constructor of Patient class.
     * @param id id of the patient
     * @param firstName first name
     * @param lastName last name
     * @param email email address
     * @param cell cell number in string
     */
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
        this.concerns.add(concern);

    }

    public void updateConcern(Concern concern) {
        //TODO

    }

    public void deleteConcern(Concern concern) {
        this.concerns.remove(concern);
    }
}
