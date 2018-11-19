package com.example.symptologger;

import java.util.ArrayList;

/**
 * Care Provider model. Extending User class.
 */
public class CareProvider extends User {
    private ArrayList<Patient> patients;

    public CareProvider(String id, String firstName, String lastName, String email, String cell) {
        super(id, firstName, lastName, email, cell);
        this.patients = new ArrayList<>();
    }

    private int assigneeCount;

    public ArrayList<Patient> getAssignedPatients() {
        return this.patients;
    }

    public void addPatient(Patient p) {
        this.patients.add(p);
    }

    public ArrayList<Concern> searchConcerns(String typed) {
        return new ArrayList<Concern>();
    }
}

