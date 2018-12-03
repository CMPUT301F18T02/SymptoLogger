package com.example.symptologger;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class ChatLogsTest {

    @Test
    public void testGetParticipantsID() {
        String[] users = {"UNIT_TESTER1", "UNIT_TESTER2"};
        ChatLogs logs = new ChatLogs("RECORD_ID", users, "UNIT TESTING", new Date().toString());

        String[] ids = logs.getParticipantsID();

        assertEquals(users, ids);
    }

    @Test
    public void testGetMessage() {
        String[] users = {"UNIT_TESTER1", "UNIT_TESTER2"};
        String message = "UNIT_TESTING";
        ChatLogs logs = new ChatLogs("RECORD_ID", users, message, new Date().toString());

        String msg = logs.getMessage();

        assertEquals(msg,message);
    }

    @Test
    public void testGetTimestamp() {
        String[] users = {"UNIT_TESTER1", "UNIT_TESTER2"};
        String stamp = new Date().toString();
        ChatLogs logs = new ChatLogs("RECORD_ID", users, "UNIT TESTING", stamp);

        String stmp = logs.getTimestamp();

        assertEquals(stmp,stamp);
    }

    @Test
    public void testGetSender() {
        String[] users = {"UNIT_TESTER1", "UNIT_TESTER2"};
        ChatLogs logs = new ChatLogs("RECORD_ID", users, "UNIT TESTING", new Date().toString());

        String sender = logs.getSender();

        assertEquals(sender,users[0]);
    }

}
