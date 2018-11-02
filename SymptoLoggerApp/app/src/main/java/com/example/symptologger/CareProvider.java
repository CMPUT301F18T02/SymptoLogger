package com.example.symptologger;

import java.util.ArrayList;

public class CareProvider extends User {
    private ArrayList<Patient> patients;

    private int assigneeCount;

    public ArrayList<Patient> getAssignedPatients() {
        return patients;
    }

    public void addPatient(Patient p) {

    }

    public ArrayList<Concern> searchConcerns(String typed) {
        return new ArrayList<Concern>();
    }
}

