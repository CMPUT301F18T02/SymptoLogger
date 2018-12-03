package com.example.symptologger;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
 * Controller for list of patients.
 */
public class PatientList {
    private ArrayList<Patient> patients;

    /**
     * Constructor
     */
    public PatientList(String cpID) {
        ElasticSearchClient.GetPatients getESPatients = new ElasticSearchClient.GetPatients();
        getESPatients.execute(cpID);
        try {
            this.patients = getESPatients.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.patients == null){
            this.patients = new ArrayList<Patient>();
        }
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
     * @param p patient object
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
