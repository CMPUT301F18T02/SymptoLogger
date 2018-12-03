package com.example.symptologger;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class ChatManager {
    private Timer timer = new Timer();
    private final int period = 15000;

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
