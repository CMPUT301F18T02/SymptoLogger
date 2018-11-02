package com.example.symptologger;

import java.util.ArrayList;

public class PatientList {
    private ArrayList<Patient> patients;

    public void addPatient(Patient p) {

    }

    public void removePatient(Patient p) {}

    public int getPatientsCount() {
        return patients.size();
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
