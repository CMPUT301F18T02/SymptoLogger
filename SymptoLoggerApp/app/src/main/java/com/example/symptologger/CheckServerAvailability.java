package com.example.symptologger;

import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class CheckServerAvailability {

    private static final String server = "192.30.35.214";
    private static final int port = 8080;
    private static boolean connectionStatus = false;

    public static void startIsOnlineTimer() {

        Timer timer = new Timer();
        timer.schedule(executeIsAvailable, 0, 10000);
    }

    public static TimerTask executeIsAvailable = new TimerTask() {
        @Override
        public void run() {
            isAvailable();
            //DO SOMETHING i.e UPDATE VIEWS
        }
    };

    private static void isAvailable() {
        try (Socket socket = new Socket(server, port)) {
            connectionStatus = true;
            return;
        } catch (Exception e) {
            connectionStatus = false;
        }
    }
}
