package com.example.symptologger;

import java.util.ArrayList;

/**
 * Controller for list of patients.
 */
public class PatientList {
    private ArrayList<Patient> patients;

    /**
     * Constructor
     */
    public PatientList() {
        this.patients = new ArrayList<>();
    }

    /**
     * Add a patient to the list
     * @param p patient object
     */
    public void addPatient(Patient p) {
        this.patients.add(p);
    }

    /**
     * Remove a patient
     * @param p patient obejct
     */
    public void removePatient(Patient p) {
        this.patients.remove(p);
    }

    /**
     * Get the count of patients
     * @return count
     */
    public int getPatientsCount() {
        return patients.size();
    }

    /**
     * Get the list of patients
     * @return a list of patients
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }
}
