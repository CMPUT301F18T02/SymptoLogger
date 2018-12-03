package com.example.symptologger;

public class ChatLogs {

    private String recordID;
    private String[] participantsID;
    private String message;
    private String timestamp;

    public ChatLogs(String recordID, String[] participantsID, String message, String timestamp){
        this.recordID = recordID;
        this.participantsID = participantsID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getRecordID() {return recordID;}
    public String[] getParticipantsID() {return participantsID;}
    public String getMessage() {return message;}
    public String getTimestamp() {return timestamp;}
    public String getSender() {return participantsID[0];}

}
