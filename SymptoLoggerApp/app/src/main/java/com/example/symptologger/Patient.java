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
     * @param user_type type of user
     */
    public Patient(String id, String firstName, String lastName, String email, String cell, String user_type) {
        super(id, firstName, lastName, email, cell, user_type);

        this.concerns = new ArrayList<>();
    }

    public void setId(String userPrompt) {
        try {
            super.setId(userPrompt);
        } catch (UserIDTooShortException e) {
            e.printStackTrace();
        }
    }

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
