package com.example.symptologger;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CareProviderTest {
    @Test
    public void testGetAssignedPatients() {
        CareProvider careProvider = new CareProvider("CP_UNITTEST","test@test.com", "123456789", "Care Provider");
        Patient patient1 = new Patient("testPatient1","test@test.com", "123456789", "Patient");
        Patient patient2 = new Patient("testPatient2","test@test.com", "123456789", "Patient");

        careProvider.addPatient(patient1);
        careProvider.addPatient(patient2);

        ArrayList<Patient> expected = new ArrayList<Patient>();
        expected.add(patient1);
        expected.add(patient2);
        assertEquals(expected, careProvider.getAssignedPatients());
    }
}
