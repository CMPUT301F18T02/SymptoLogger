package com.example.symptologger;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


/**
 * The controller for the patient list.
 *
 * @author Patrick Tamm
 */
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
