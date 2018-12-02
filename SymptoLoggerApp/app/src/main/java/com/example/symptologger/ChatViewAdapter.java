package com.example.symptologger;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

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