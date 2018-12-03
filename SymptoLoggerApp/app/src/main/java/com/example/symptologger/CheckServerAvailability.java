package com.example.symptologger;

import android.util.Log;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class CheckServerAvailability {

    private static final String server = "192.30.35.214";
    private static final int port = 8080;
    private static boolean connectionStatus = false;
    private static boolean timerStart = false;

    private static TimerTask executeIsAvailable = new TimerTask() {
        @Override
        public void run() {
            isAvailable();
            //DO SOMETHING
            Log.d("SERVER", String.valueOf(connectionStatus));
        }
    };

    public static void startIsAvailableTimer() {

        Timer timer = new Timer();
        timerStart = true;
        timer.schedule(executeIsAvailable, 0, 10000);
    }

    private static void isAvailable() {
        try (Socket socket = new Socket(server, port)) {
            connectionStatus = true;
            return;
        } catch (Exception e) {
            connectionStatus = false;
        }
    }

    public static boolean getConnectionStatus() {
        return connectionStatus;
    }

    public static boolean getTimerStart () {
        return timerStart;
    }
}