package com.example.symptologger;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserListTest {
    @Test
    public void testAddUser() {
        UserList ulist = new UserList();
        User user = new User();

        assertFalse(ulist.getUserList().contains(user));

        ulist.addUser(user);
        assertTrue(ulist.getUserList().contains(user));
    }

    @Test
    public void testRemoveUser() {
        UserList ulist = new UserList();
        User user = new User();
        ulist.addUser(user);
        assertTrue(ulist.getUserList().contains(user));

        ulist.removeUser(user);
        assertFalse(ulist.getUserList().contains(user));
    }

    @Test
    public void testGetUserList() {
        UserList userList = new UserList();
        User user1 = new User();
        User user2 = new User();

        ArrayList<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        assertEquals(expected, userList.getUserList());
    }

    @Test
    public void testGetUserByPos() {
        // TODO
    }
}
