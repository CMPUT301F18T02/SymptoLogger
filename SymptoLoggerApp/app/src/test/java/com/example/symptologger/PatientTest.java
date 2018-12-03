package com.example.symptologger;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientTest {

    @Test
    public void testGetConcerns() {
        Patient patient = new Patient("testCarePatient", "test@test.com", "123456789", "patient");
        Concern concern1 = null;
        try {
            concern1 = new Concern("UNIT_TESTER","UNIT_TEST");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }
        Concern concern2 = null;
        try {
            concern2 = new Concern("UNIT_TESTER2","UNIT_TEST2");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        patient.addConcern(concern1);
        patient.addConcern(concern2);

        ArrayList<Concern> expected = new ArrayList<>();
        expected.add(concern1);
        expected.add(concern2);

        assertEquals(expected, patient.getConcerns());

    }

    @Test
    public void testAddConcern() {
        Patient patient = new Patient("testCarePatient", "test@test.com", "123456789", "patient");
        Concern concern = null;
        try {
            concern = new Concern("UNIT_TESTER","UNIT_TEST");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        assertFalse(patient.getConcerns().contains(concern));

        patient.addConcern(concern);
        assertTrue(patient.getConcerns().contains(concern));
    }

    @Test
    public void testDeleteConcern() {
        Patient patient = new Patient("testCarePatient", "test@test.com", "123456789", "patient");
        Concern concern = null;
        try {
            concern = new Concern("UNIT_TESTER","UNIT_TEST");
        } catch (TitleTooLongException e) {
            e.printStackTrace();
        }

        assertFalse(patient.getConcerns().contains(concern));

        patient.addConcern(concern);
        assertTrue(patient.getConcerns().contains(concern));
    }
}
