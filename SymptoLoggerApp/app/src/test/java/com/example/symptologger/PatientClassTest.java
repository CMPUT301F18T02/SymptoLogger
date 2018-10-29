package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientClassTest {
    @Test
    public void testSetFirstName() {
        Patient patient = new Patient();
        String expectedName = "FisrtName";
        patient.setFirstName(expectedName);

        assertEquals(expectedName, patient.firstName);
    }

    @Test
    public void testSetLastName() {
        Patient patient = new Patient();
        String expectedName = "LastName";
        patient.setLastName(expectedName);

        assertEquals(expectedName, patient.lastName);
    }

    @Test
    public void testGetFirstName() {
        Patient patient = new Patient();
        String expectedName = "FisrtName";
        patient.setFirstName(expectedName);
        String result = patient.getFirstName();

        assertEquals(expectedName, result);
    }

    @Test
    public void testGetLastName() {
        Patient patient = new Patient();
        String expectedName = "LastName";
        patient.setLastName(expectedName);
        String result = patient.getLastName();

        assertEquals(expectedName, result);
    }

    @Test
    public void testCreateValidUserID() {
        Patient patient = new Patient();
        String expected = "validName";
        patient.createUserID(expected);

        assertEquals(expected, patient.getUserID());
    }

    @Test
    public void testCreateInvalidUserID() {
        Patient patient = new Patient();
        boolean thrown = false;
        String expected = "short";

        try {
            patient.createUserID(expected);
        } catch(RuntimeException e) {
            thrown = true;
        }

        assertTrue(thrown);

    }

    @Test
    public void testSetUserID() {
        Patient patient = new Patient();
        String expected = "validID";
        patient.setUserID(expected);

        assertEquals(expected, patient.getUserID());
    }
}
