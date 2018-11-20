package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void testSetFirstName() {
        Patient patient = new Patient("testCarePatient", "First", "Last",
                "test@test.com", "123456789", "user");
        String expectedName = "FisrtName";
        patient.setFirstName(expectedName);

        assertEquals(expectedName, patient.getFirstName());
    }

    @Test
    public void testSetLastName() {
        Patient patient = new Patient("testCarePatient", "First", "Last",
                "test@test.com", "123456789", "user");
        String expectedName = "LastName";
        patient.setLastName(expectedName);

        assertEquals(expectedName, patient.getLastName());
    }

    @Test
    public void testGetFirstName() {
        Patient patient = new Patient("testCarePatient", "First", "Last",
                "test@test.com", "123456789", "user");
        String expectedName = "FisrtName";
        patient.setFirstName(expectedName);
        String result = patient.getFirstName();

        assertEquals(expectedName, result);
    }

    @Test
    public void testGetLastName() {
        Patient patient = new Patient("testCarePatient", "First", "Last",
                "test@test.com", "123456789", "user");
        String expectedName = "LastName";
        patient.setLastName(expectedName);
        String result = patient.getLastName();

        assertEquals(expectedName, result);
    }

    @Test
    public void testSetID() {
        Patient patient = new Patient("testCarePatient", "First", "Last",
                "test@test.com", "123456789", "user");
        String expected = "validID";
        patient.setId(expected);

        assertEquals(expected, patient.getId());
    }

    @Test
    public void testGetId(){
        User user = new User();

        String id = "9999";
        user.setId(id);

        assertEquals(user.getId(), id);
    }

    @Test
    public void testGetEmail(){
        User user = new User();

        String email = "John@ualberta.ca";
        user.setEmail(email);

        assertEquals(user.getEmail(), email);

    }

    @Test
    public void testGetCell(){
        User user = new User();

        String cell = "7800000000";
        user.setCell(cell);

        assertEquals(user.getCell(), cell);
    }
}
