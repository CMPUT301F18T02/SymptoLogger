package com.example.symptologger;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
 * ChatViewAdapter is the adapter for the chat window.
 *
 * @author Remi Arshad
 * @see ChatLogs, ChatManager
 */
public class ChatViewAdapter extends RecyclerView.Adapter<ChatViewAdapter.ViewHolder> {
    private List<List<ChatLogs>> chatLogs;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView chatBox, senderBox, timeStampBox;

        public ViewHolder(View view) {
            super(view);
            chatBox = view.findViewById(R.id.chatbox);
            senderBox = view.findViewById(R.id.sender);
            timeStampBox = view.findViewById(R.id.timestamp);

        }
    }

    public ChatViewAdapter(List<List<ChatLogs>> chatLogs) {
        this.chatLogs = chatLogs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sender, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder chatLogHolder, int position) {

        ChatLogs chatLog = chatLogs.get(chatLogs.size() - 1).get(position);
        chatLogHolder.chatBox.setText(chatLog.getMessage());
        chatLogHolder.senderBox.setText(chatLog.getSender());
        chatLogHolder.timeStampBox.setText(chatLog.getTimestamp().substring(0,5));
        if (chatLogs.size() > 1){
            chatLogs.remove(0);
        }
    }

    @Override
    public int getItemCount() {
        if (chatLogs.size() - 1 < 0) {
            return chatLogs.size();
        } else {
            return chatLogs.get(chatLogs.size() - 1).size();
        }
    }
}