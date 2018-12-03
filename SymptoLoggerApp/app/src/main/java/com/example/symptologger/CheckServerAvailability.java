package com.example.symptologger;

import android.util.Log;

import java.net.Socket;
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
 * CheckServerAvailability determines if there is connectivity to the server, or if it is offline.
 *
 * @author Remi Arshad
 */
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