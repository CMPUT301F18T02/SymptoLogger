package com.example.symptologger;

import org.junit.Test;
import org.junit.experimental.categories.Categories;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CareProviderTest {
    @Test
    public void testAddPatient() {
        CareProvider careProvider = new CareProvider("testCareProvider", "First", "Last",
                "test@test.com", "123456789");
        Patient patient = new Patient("testPatient", "First", "Last",
                "test@test.com", "123456789");

        assertFalse(careProvider.getAssignedPatients().contains(patient));

        careProvider.addPatient(patient);
        assertTrue(careProvider.getAssignedPatients().contains(patient));
    }

    @Test
    public void testGetAssignedPatients() {
        CareProvider careProvider = new CareProvider("testCareProvider", "First", "Last",
                "test@test.com", "123456789");
        Patient patient1 = new Patient("testPatient1", "First", "Last",
                "test@test.com", "123456789");
        Patient patient2 = new Patient("testPatient2", "First", "Last",
                "test@test.com", "123456789");

        careProvider.addPatient(patient1);
        careProvider.addPatient(patient2);

        ArrayList<Patient> expected = new ArrayList<Patient>();
        expected.add(patient1);
        expected.add(patient2);
        assertEquals(expected, careProvider.getAssignedPatients());
    }

    @Test
    public void testSearchConcerns() {
        CareProvider careProvider = new CareProvider("testCareProvider", "First", "Last",
                "test@test.com", "123456789");
        ArrayList<Concern> concerns = new ArrayList<>();
        Concern concern1 = new Concern();
        concern1.setTitle("Testing");
        concerns.add(concern1);

        assertEquals(concerns, careProvider.searchConcerns("Testing"));
    }
}
