package com.example.symptologger;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetId(){
        User user = new User();

        String id = "9999";
        user.setId(id);

        assertEquals(user.getId(), id);
    }

    @Test
    public void testGetName(){
        User user = new User();

        String name = "John";
        user.setName(name);

        assertEquals(user.getName(), name);

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
