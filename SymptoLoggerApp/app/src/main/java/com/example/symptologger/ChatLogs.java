package com.example.symptologger;

public class ChatLogs {

    private String[] participantsID;
    private String message;
    private String timestamp;

    public ChatLogs(String[] participantsID, String message, String timestamp){
        this.participantsID = participantsID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String[] getParticipantsID() {return participantsID;}
    public String getMessage() {return message;}
    public String getTimestamp() {return timestamp;}
    public String getSender() {return participantsID[0];}



}
