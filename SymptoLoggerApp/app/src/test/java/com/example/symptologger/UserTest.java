package com.example.symptologger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testSetID() {
        User user = new User("UNIT_TESTER","test@test.com", "123456789", "user");
        String expected = "UNIT_TESTER!!!";
        try {
            user.setUserID(expected);
        } catch (UserIDTooShortException e) {
            e.printStackTrace();
        }

        assertEquals(expected, user.getUserID());
    }

    @Test
    public void testGetId(){
        User user = new User();

        String id = "UNIT_TESTER";
        try {
            user.setUserID(id);
        } catch (UserIDTooShortException e) {
            e.printStackTrace();
        }

        assertEquals(user.getUserID(), id);
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
