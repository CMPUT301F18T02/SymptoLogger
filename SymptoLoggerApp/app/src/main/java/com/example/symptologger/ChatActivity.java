package com.example.symptologger;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ChatActivity extends AppCompatActivity {
    private TextView textBox;
    private EditText messageBox;

    private String senderID = "test1234";
    private String receiverID = "patrick1";
    private String message;
    private Date date;

    public static boolean updateLogsReady = false;
    public static List<List<ChatLogs>> chatLogs = new ArrayList<>();


    private static RecyclerView recyclerView;
    private ChatManager cm;
    private static ChatViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //Button send = findViewById(R.id.send);
        messageBox = findViewById(R.id.editText);
        textBox = findViewById(R.id.chatbox);

        recyclerView = findViewById(R.id.chatlogs_holder);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ChatViewAdapter(chatLogs);
        recyclerView.setAdapter(mAdapter);

        cm = new ChatManager(this);
        cm.startFetchLogsTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();  // calls the superclass method
        cm.stopFetchLogsTimer();
    }


    public void send(View v) { //USE LISTENER METHOD INSTEAD ON FINAL PROJECT
        cm.stopFetchLogsTimer();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("MST"));
        date = new Date();

        message = messageBox.getText().toString();
        System.out.println(message);

        ChatManager.setSaveVars(senderID, receiverID, message, formatter.format(date));
        ChatManager.saveLogs();
        cm.restartTimer();
    }

    public static void updateView() {
        System.out.println("-----status-----");
        System.out.println(updateLogsReady);
        if (updateLogsReady == true) {
            updateLogsReady = false;
            mAdapter.notifyDataSetChanged();
            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, mAdapter.getItemCount()-1);
        }
    }

}