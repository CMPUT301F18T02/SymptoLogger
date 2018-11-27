package com.example.symptologger;

public class PatientListController {
    private static PatientList patientList = null;

    static public PatientList getPatientList(){
        if (patientList == null){
            patientList = new PatientList();
        }
        return patientList;
    }

    public void addPatient(Patient patient){
        getPatientList().addPatient(patient);
    }

    public void deletePatient(Patient patient){
        getPatientList().removePatient(patient);
    }

    public int getPatientsCount(){
        return getPatientList().getPatientsCount();
    }
}
