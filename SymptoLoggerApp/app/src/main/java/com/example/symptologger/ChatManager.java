package com.example.symptologger;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

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
 * ChatManager handles the chat feature between patients and care providers. The view will be updated
 * frequently whil there is server connectivity, to implement real-time, or close to real-time,
 * interaction.
 *
 * @author Remi Arshad
 * @see ChatLogs
 */
public class ChatManager {
    private Timer timer = new Timer();
    private final int period = 7000;

    private static String recordID;
    private static String receiverID;
    private static String senderID;
    private static String date;
    private static String message;

    private static Activity activity;

    public ChatManager(Activity activity){
        this.activity = activity;
    }

    public TimerTask executeFetchLogs = new TimerTask() {
        @Override
        public void run() {
            fetchLogs();
        }
    };

    public void startFetchLogsTimer() {
        timer.schedule(executeFetchLogs, 0, period);
    }

    public void stopFetchLogsTimer() {
        timer.cancel();
        timer.purge();
    }

    public void restartTimer() {
        timer = new Timer();
        executeFetchLogs = new TimerTask() {
            @Override
            public void run() {
                fetchLogs();
            }
        };
        timer.schedule( executeFetchLogs, 1000, period );
    }

    public void setFetchInfo(String rID, String sID, String receID) {
        recordID = rID;
        senderID = sID;
        receiverID = receID;
    }

    public void fetchLogs() {
        ElasticSearchClient.FetchChatLogs fetch = new ElasticSearchClient.FetchChatLogs();
        fetch.execute(recordID, senderID,receiverID);

        System.out.println("FETCHING LOGS...");
    }


    public static void saveLogs() {
        ElasticSearchClient.SaveChatLog save = new ElasticSearchClient.SaveChatLog();
        save.execute(recordID, senderID, receiverID, message, date);
        System.out.println("SAVING LOGS...");
    }

    public static void setSaveVars(String rID, String sid, String rid, String msg, String timestamp){
        recordID = rID;
        senderID = sid;
        receiverID = rid;
        message = msg;
        date = timestamp;
    }

    final static Runnable refreshView = () ->
            RecordCommentFragment.updateView();

    public static void callViewUpdate(){
        activity.runOnUiThread(refreshView);
    }
}
