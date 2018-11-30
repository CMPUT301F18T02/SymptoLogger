package com.example.symptologger;

public class PatientListController {
    private static PatientList patientList;

    static public PatientList getPatientList(String cpID){
        patientList = new PatientList(cpID);
        return patientList;
    }

    public void addPatient(Patient patient, String cpID){
        getPatientList(cpID).addPatient(patient);
    }

    public void deletePatient(Patient patient, String cpID){
        getPatientList(cpID).removePatient(patient);
    }

    public int getPatientsCount(String cpID){
        return getPatientList(cpID).getPatientsCount();
    }
}
