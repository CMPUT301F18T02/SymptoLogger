package com.example.symptologger;

import java.util.ArrayList;

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
 * Care Provider model. Extending User class.
 */
public class CareProvider extends User {
    //private PatientList patientList;
    private ArrayList<Patient> patients;
    private int assigneeCount;

    public CareProvider(String id, String email, String cell, String user_type) {
        super(id, email, cell, user_type);
        this.patients = new ArrayList<Patient>();
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

}

