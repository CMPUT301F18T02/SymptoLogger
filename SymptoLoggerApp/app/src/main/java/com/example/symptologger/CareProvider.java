package com.example.symptologger;

import java.util.ArrayList;

/**
 * Care Provider model. Extending User class.
 */
public class CareProvider extends User {
    private ArrayList<Patient> patients;
    private int assigneeCount;

    public CareProvider(String id, String firstName, String lastName, String email, String cell, String user_type) {
        super(id, firstName, lastName, email, cell, user_type);
    }

    /**
     * Gets the list of a care provider's assigned patients
     * @return patients
     */

    public ArrayList<Patient> getAssignedPatients() {
        return this.patients;
    }

    /**
     * Enables the adding of a new patient to the patient list.
     * @param p the new patient to be added.
     */

    public void addPatient(Patient p) {
        this.patients.add(p);
    }

    /**
     * Allows care providers to search through a patient's concerns.
     * @param typed the search criteria.
     * @return ArrayList<Concern> a list of the concerns matching the search criteria.
     */

    //NOT IN VERSION 1

    public ArrayList<Concern> searchConcerns(String typed) {
        return new ArrayList<Concern>();
    }
}

