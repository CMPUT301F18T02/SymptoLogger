package com.example.symptologger;

/*
 *  Copyright 2018 Remi Arshad, Noni Hua, Jason Lee, Patrick Tamm, Kaiwen Zhang
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * ChatLogs represents the log of comments between patients and their care providers. Patients and
 * care providers are able to add comments to records; thios has been implemented as a chat feature
 * between the two.
 *
 * @author Remi Arshad
 * @see ChatManager
 */
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
