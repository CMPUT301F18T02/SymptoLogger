package com.example.symptologger;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatientListTest {
    @Test
    public void testAddPatient() {
        PatientList patientList = new PatientList();
        Patient p = new Patient();

        assertFalse(patientList.getPatients().contains(p));

        patientList.addPatient(p);
        assertTrue(patientList.getPatients().contains(p));
    }

    @Test
    public void testRemovePatient() {
        PatientList patientList = new PatientList();
        Patient p = new Patient();

        patientList.addPatient(p);
        assertTrue(patientList.getPatients().contains(p));

        patientList.removePatient(p);

        assertFalse(patientList.getPatients().contains(p));
    }

    @Test
    public void testGetPatientList() {
        PatientList patientList = new PatientList();
        Patient p1 = new Patient();
        Patient p2 = new Patient();

        patientList.addPatient(p1);
        patientList.addPatient(p2);

        ArrayList<Patient> expected = new ArrayList<>();
        expected.add(p1);
        expected.add(p2);

        assertEquals(expected, patientList.getPatients());
    }

    @Test
    public void testGetPatientSCount() {
        PatientList patientList = new PatientList();
        Patient p1 = new Patient();
        Patient p2 = new Patient();

        patientList.addPatient(p1);
        patientList.addPatient(p2);

        int expected = 2;
        assertEquals(expected, patientList.getPatientsCount());
    }
}
